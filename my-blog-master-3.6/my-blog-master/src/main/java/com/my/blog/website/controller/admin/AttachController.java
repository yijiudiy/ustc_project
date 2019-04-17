package com.my.blog.website.controller.admin;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dao.ContentVoMapper;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.model.Bo.RestResponseBo;
import com.my.blog.website.model.Vo.AttachVo;
import com.my.blog.website.model.Vo.ContentVo;
import com.my.blog.website.model.Vo.UserVo;
import com.my.blog.website.service.IAttachService;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.utils.Commons;
import com.my.blog.website.utils.TaleUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 附件管理
 * <p>
 * Created by 13 on 2017/2/21.
 */
@Controller
@RequestMapping("admin/attach")
public class AttachController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();

    @Resource
    private IAttachService attachService;

    @Resource
    private IContentService contentService;
    @Resource
    private ILogService logService;

    /**
     * 附件页面
     *
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit) {
        PageInfo<AttachVo> attachPaginator = attachService.getAttachs(page, limit);
        request.setAttribute("attachs", attachPaginator);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType(), Commons.site_url()));
        request.setAttribute("max_file_size", WebConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

    /**
     * 上传文件接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "upload" )
    @ResponseBody
    public String upload(HttpServletRequest request) throws IOException {
        List<MultipartFile> multipartFiles = ((MultipartHttpServletRequest) request).getFiles("file");
        UserVo users = this.user(request);
        Integer uid = users.getUid();
        List<String> errorFiles = new ArrayList<>();
        ContentVo[] contents = new ContentVo[multipartFiles.size()+1];
        int k=0;
        try {
            for (MultipartFile multipartFile : multipartFiles) {
                String fname = multipartFile.getOriginalFilename();
                if (multipartFile.getSize() <= WebConst.MAX_FILE_SIZE) {
                    String fkey = TaleUtils.getFileKey(fname);
                    String ftype = TaleUtils.isImage(multipartFile.getInputStream()) ? Types.IMAGE.getType() : Types.FILE.getType();
                    File file = new File(CLASSPATH + fkey);
                    try {
                        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));//将文件拷贝到服务器
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //下面的内容用于读取文件到字符串
                    FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
                    BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
                    StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
                    String s = "";
                    while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                        sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                        System.out.println(s);
                    }
                    bReader.close();
                    String str = sb.toString();
                    ContentVo content = new ContentVo();
//                    String title =fname.substring(fname.indexOf(".")+1, fname.length());
                    String title =fname.substring(0, fname.indexOf("."));
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(date);


//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = sdf.parse(time);



                    //content.setTitle(title + "_" + dateString );
                    content.setTitle(title);
                    content.setType("post");
                    content.setStatus("publish");
                    content.setCategories("C++");
                    content.setContent(str);
                    content.setAllowFeed(true);
                    content.setAllowComment(true);
                    content.setAllowPing(true);
                    content.setTags("");
                    content.setAuthorId(users.getUid());
                    contents[k++] = content;
                   // contentService.publish(content);
                    //
                    attachService.save(fname, fkey, ftype, uid);//保存上传的文件的基本信息，和路径信息,其中fkey包含了路径信息
                } else {
                    errorFiles.add(fname);
                }
            }
            contentService.upload(contents);
        } catch (Exception e) {
            //return RestResponseBo.fail();
            return "失败";
        }
       // return RestResponseBo.ok(errorFiles);
        return "上传成功";
    }








    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam Integer id, HttpServletRequest request) {
        try {
            AttachVo attach = attachService.selectById(id);
            if (null == attach) {
                return RestResponseBo.fail("不存在该附件");
            }
            attachService.deleteById(id);
            new File(CLASSPATH + attach.getFkey()).delete();
            logService.insertLog(LogActions.DEL_ARTICLE.getAction(), attach.getFkey(), request.getRemoteAddr(), this.getUid(request));
        } catch (Exception e) {
            String msg = "附件删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
