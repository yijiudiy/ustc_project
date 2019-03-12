package com.my.blog.website.utils.liusongcode;
import java.text.NumberFormat;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.io.*;
public class CodeMeasure {
	public  StringBuffer res=new StringBuffer();
	//字符串读取
	public  String lsmea(String s){
		//res.delete(0,res.length());
		FileWriter fw = null;
		File f = new File("a.txt");

		try {
			if(!f.exists()){
				f.createNewFile();
			}
			fw = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(s, 0, s.length()-1);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println("项目名称："+f);
		//System.out.println("开始！");
		//res.append("总行数:"+String.valueOf(line)+"\r\n");

		StringBuffer comBuf = new StringBuffer(new CommitLineNumber(f).showResult());
		res.append(comBuf);
		//显示类个数和函数个数

		int branchResult=0,stateRusult=0;
		FunctionNumber fun=new FunctionNumber(f);
		res.append(fun.showResult());
		int classNum=fun.getClassNum();//读取类数和函数数 方便下面语句计算
		int funNum=fun.getFunctionNum();

		//显示语句数和分支比例
		StatementsNumber sta = new StatementsNumber(f);
		sta.showResult();
		int statementsNum=sta.getStatementsNum();
		int branchNum=sta.getBranchNum();
		statementsNum+=classNum;//这里语句数要加上类和函数的声明语句
		statementsNum+=funNum;

		float t=branchNum;
		float branchPercent=t/statementsNum;
		NumberFormat nt = NumberFormat.getPercentInstance();
		res.append("总语句数："+statementsNum+"\r\n");
		//System.out.println("总语句数："+statementsNum);

		if(statementsNum!=0)
			res.append("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent)+"\r\n"+"\r\n");
		//System.out.println("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent));
		f.delete();
		return res.toString();
	}
	//文件读取
	public  String filemea(File f){
		StringBuffer comBuf = new StringBuffer(new CommitLineNumber(f).showResult());
		res.append(comBuf);
		//显示类个数和函数个数

		int branchResult=0,stateRusult=0;
		FunctionNumber fun=new FunctionNumber(f);
		res.append(fun.showResult());
		int classNum=fun.getClassNum();//读取类数和函数数 方便下面语句计算
		int funNum=fun.getFunctionNum();

		//显示语句数和分支比例
		StatementsNumber sta = new StatementsNumber(f);
		sta.showResult();
		int statementsNum=sta.getStatementsNum();
		int branchNum=sta.getBranchNum();
		statementsNum+=classNum;//这里语句数要加上类和函数的声明语句
		statementsNum+=funNum;

		float t=branchNum;
		float branchPercent=t/statementsNum;
		NumberFormat nt = NumberFormat.getPercentInstance();
		res.append("总语句数："+statementsNum+"\r\n");
		//System.out.println("总语句数："+statementsNum);

		if(statementsNum!=0)
			res.append("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent)+"\r\n"+"\r\n");
		//System.out.println("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent));
		f.delete();
		return res.toString();
	}
}