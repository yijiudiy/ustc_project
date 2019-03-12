package com.my.blog.website.utils.liusongcode;

import java.text.NumberFormat;
import java.util.*;
import java.io.*;
public class CommitLineNumber {
	static File file_path;
	public  StringBuffer res=new StringBuffer();

	CommitLineNumber(File s)
	{
		file_path=s;
	}

	public  StringBuffer showResult(){


		int lineCount=0,blankLines=0,commentLines=0,codeLines=0,constNum=0,staticNum=0;
		BufferedReader br = null;
		boolean flag = false;
		try {
			br = new BufferedReader(new FileReader(file_path));
			String line = "";
			while ((line = br.readLine()) != null) {
				lineCount++;
				line = line.trim(); // 除去注释前的空格
				if (line.matches("^[ ]*$")) { // 匹配空行
					blankLines++;
				} else if (/*line.startsWith("//")*/line.indexOf("//")>=0) {
					commentLines++;
				}  else if (line.startsWith("/*") || flag==true)
				{  //只计算这种形式的注释块
					commentLines++;
					flag = true;   //标价是否在注释块中
					if(line.endsWith("*/"))flag=false;
				}
				else {
					String lowLine=line.toLowerCase();
					codeLines++;   //剩下的是代码行
					if(lowLine.indexOf("const")>=0 )
						constNum++;
					if(lowLine.indexOf("static")>=0)
						staticNum++;

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("有文件输入错误，请核对！"); //检查到文件不存在，提示错误
			System.exit(0); //结束程序
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		res.append("总行数:"+lineCount+"\r\n");
		res.append("空行数："+blankLines+"\r\n");
		res.append("代码行数："+codeLines+"\r\n");


		//显示空行数和代码行数，返回注释行数


		float t=commentLines;
		float commentPercent=t/lineCount;
		//获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		//设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		//System.out.println("注释行数:"+comment+"  注释行数比例"+nt.format(commentPercent));
		res.append("注释行数:"+commentLines+"  注释行数比例"+nt.format(commentPercent)+"\r\n");

		res.append("const常量个数："+constNum+"\r\n");
		res.append("static常量个数："+staticNum+"\r\n");
		return res;
	}
}

