package com.my.blog.website.controller.admin;


import com.github.pagehelper.PageInfo;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dao.AttachVoMapper;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.model.Bo.RestResponseBo;
import com.my.blog.website.model.Vo.*;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IMetaService;
import com.my.blog.website.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 1 on 2019/2/21.
 */
@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();
    @Resource
    private IContentService contentsService;

    @Resource
    private IMetaService metasService;
    @Resource
    private AttachVoMapper attachDao;

    @Resource
    private ILogService logService;

    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc");
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample, page, limit);
        request.setAttribute("articles", contentsPaginator);
        return "admin/article_list";
    }

    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        return "admin/article_edit";
    }

    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, HttpServletRequest request) {
        ContentVo contents = contentsService.getContents(cid);
        request.setAttribute("contents", contents);
        List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    @PostMapping(value = "/publish")
    @ResponseBody
    public RestResponseBo publishArticle(ContentVo contents, HttpServletRequest request) {
        UserVo users = this.user(request);
        contents.setAuthorId(users.getUid());
        contents.setType(Types.ARTICLE.getType());
        if (StringUtils.isBlank(contents.getCategories())) {
            contents.setCategories("默认分类");
        }
        String result = contentsService.publish(contents);
        if (!WebConst.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "/modify")
    @ResponseBody
    public RestResponseBo modifyArticle(ContentVo contents, HttpServletRequest request) {
        UserVo users = this.user(request);
        contents.setAuthorId(users.getUid());
        contents.setType(Types.ARTICLE.getType());
        String result = contentsService.updateArticle(contents);
        if (!WebConst.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        String result = contentsService.deleteByCid(cid);
        logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));
        if (!WebConst.SUCCESS_RESULT.equals(result)) {
            return RestResponseBo.fail(result);
        }
        return RestResponseBo.ok();
    }

    //分析结果下载
    //edited by ：钟顺超 time :2019.2.23 7:27PM

    @GetMapping("/download/{cid}") //id为时间戳@RequestParam
    @ResponseBody
    public void download(@PathVariable String cid ,HttpServletRequest request, HttpServletResponse response) {
        ContentVo contents = contentsService.getContents(cid);//获取文章内容
        String title = contents.getTitle();//获取文章标题
        try (
                //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                InputStream inputStream = new ByteArrayInputStream(contents.getContent().getBytes());
                //InputStream inputStream = new FileInputStream(new File(CLASSPATH + fkey));
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            //String fileName = "test.txt";
            response.addHeader("Content-Disposition", "attachment;fileName=" + title + ".txt");   // 设置文件名


            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //下载上传的文件
    //edited by ：钟顺超 time :2019.2.23 7:27PM

    @GetMapping("/downloadUnloadedFile/{cid}") //id为时间戳@RequestParam
    @ResponseBody
    public void download(@PathVariable Integer cid ,HttpServletRequest request, HttpServletResponse response) {
        //ContentVo contents = contentsService.getContents(cid);
        AttachVo attachVo =attachDao.selectByPrimaryKey(cid);
        String fileKey = attachVo.getFkey();
        String fileName = attachVo.getFname();

        File file = new File(CLASSPATH, fileKey);
        System.out.print(CLASSPATH+fileKey);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            try (
                    //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                    //InputStream inputStream = new ByteArrayInputStream(contents.getMeasure().getBytes());
                    InputStream inputStream = new FileInputStream(file);
                    OutputStream outputStream = response.getOutputStream()
            ) {
                //指明为下载
                response.setContentType("application/x-download");
                //String fileName = "test.txt";
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));   // 设置文件名

                //把输入流copy到输出流
                IOUtils.copy(inputStream, outputStream);

                //outputStream.flush();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            System.out.print("文件不存在");
        }

        }



    }


