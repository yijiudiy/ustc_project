package com.my.blog.website.utils.liusongcode;

import java.io.*;
public class StatementsNumber {
	static File file_path;
	int branchResult=0,stateRusult=0;
	StatementsNumber(File s)
	{
		file_path=s;
	}
	public int getBranchNum(){
		return branchResult;
	}
	public int getStatementsNum(){
		return stateRusult;
	}
	public void showResult(){
		boolean commentFlag = false;  
	    BufferedReader br = null;
	        try 
	        {  
	            br = new BufferedReader(new FileReader(file_path));
	            String line = "";  
	            while ((line = br.readLine()) != null) {
	            	line = line.trim(); 
	            	String lowLine=line.toLowerCase();
	            	
	            	if (line.startsWith("//") || line.startsWith("/*") || commentFlag==true) 
		            {    
	            	 	if(line.startsWith("/*"))commentFlag = true;   //标注是否在注释块中 
	            	 	if(line.indexOf("*/")>=0)commentFlag=false;
		            }
	            	
	            	else
	            	{	
	            		if(lowLine.indexOf("if")>=0 || lowLine.indexOf("else")>=0 ||
	    	                	lowLine.indexOf("for")>=0 || lowLine.indexOf("while")>=0)
	            		{
	            			branchResult++;
	            			stateRusult++;
	            			if(line.lastIndexOf(";")==line.length()-1)
	            				stateRusult++;
	            		}
	            		
	            		else if(
	    	                	lowLine.indexOf("break")>=0 ||	lowLine.indexOf("continue")>=0 ||
	    	                	lowLine.indexOf("goto")>=0 || lowLine.indexOf("switch")>=0 ||
	    	                	lowLine.indexOf("case")>=0 || lowLine.indexOf("default")>=0 ||
	    	                	lowLine.indexOf("return")>=0 || lowLine.indexOf("catch")>=0)
	            		{
	            			branchResult++;
	            			stateRusult++;
	            			
	            			/*
		                	 “分支语句”指的是使程序不顺序履行的语句，
		                	包括if、else、for、while、break、continue、goto、switch、case、default和return。
		                	需要注意的是，do不被计算在内，由于其对应的while已计算了。另外，异常处理的catch也被作为1个分支计算。
		                	*/
	            		}
	            		else if(line.indexOf(";")>0 || lowLine.indexOf("try")>=0 || lowLine.indexOf("finally")>=0
	            				|| lowLine.indexOf("class")>=0 || lowLine.indexOf("#include")>=0)
		                {
		                	stateRusult++;
		                }
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
	}
}

