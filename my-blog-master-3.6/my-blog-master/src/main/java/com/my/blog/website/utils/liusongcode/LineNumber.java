package com.my.blog.website.utils.liusongcode;

import java.io.*;
public class LineNumber {
	static File file_path;
	LineNumber(File s)
	{
		file_path=s;
	}
	public static int showResult(){
        int Count = 0,result=0;
        FileInputStream in = null;//声明文件字符输入流
        InputStreamReader isr = null;//声明字节输入流
        BufferedReader bis = null;//声明缓存输入流
        try {
            in = new FileInputStream(file_path);//实例化文件输入流对象
            isr = new InputStreamReader(in);//实例化字节输入流对象，实现字节流到字符流的转换
            bis = new BufferedReader(isr);//实例化缓存输入流对象，为上面的Reader提供缓冲功能
            while (bis.readLine()!=null) {
                Count++;
            }
           result=Count;
            Count = 0;
            
        } catch (FileNotFoundException e) {
            System.out.println("有文件输入错误，请核对！"); //检查到文件不存在，提示错误
            System.exit(0); //结束程序
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();//关闭文件字符输入流
                isr.close();//关闭字节输入流
                bis.close();//关闭缓存输入流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

