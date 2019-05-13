
import java.text.NumberFormat;
import java.util.*;
import java.io.*;
public class FunctionNumber {
    static File file_path;
    int functionResult=0;
    static Map<String,Integer> cla = new LinkedHashMap<String,Integer>();
    static int classNum=0;
    static int funNum=0;
    public  StringBuffer res=new StringBuffer();
    FunctionNumber(File s)
    {
        file_path=s;
    }

    public int getClassNum()
    {
        return classNum;
    }
    public int getFunctionNum()
    {
        return functionResult;
    }

    public  StringBuffer showResult(){
        int left=0,functionFlag=0,classLeft=0,functionLineNum=0,maxDepth=0,numDepth=0,
                functionStateNum=0,functionComplexSum=0,maxFunComplex=0;
        boolean commentFlag = false,singleLine=false;
        BufferedReader br = null;
        int functionLine[]= new int[100];		//函数行数
        int functionState[]= new int[100];		//函数语句数
        String funName[] = new String[100];		//函数名
        int functionComplex[] = new int[100];    //函数的圈复杂度
        int funMaxDepth[] = new int[100];    //函数的最大深度

        try
        {
            br = new BufferedReader(new FileReader(file_path));
            String line = "",preLine="";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String lowLine=line.toLowerCase();

                /**
                 * 这里是为了防止判断深度时 出现if等语句后面只有一句而没有}的情况
                 * 判断深度的基本思想是：遇到分支语句 计数器+1  遇到}-1 实时记录计数器最大值
                 */
                if(singleLine)
                {
                    if(!line.startsWith("{") && !lowLine.startsWith("for")
                            && !lowLine.startsWith("while") )
                        numDepth--;
                    //说明这个分支语句只有一行 那么就要手动把numDepth-1
                    singleLine=!singleLine;//记得把单行标志再置为相反
                }


                if(line.indexOf('{')<0 && line.indexOf('}')>=0)
                {
                    if(numDepth>0)//遇到右括号 深度要减一
                        numDepth--;
                    left--;
                    if(left==classLeft)
                    {
                        funMaxDepth[functionResult]=maxDepth;
                        functionResult++;
                        functionFlag=0;
                    }//表示函数的花括号

                    if(left<classLeft)
                    {
                        classLeft--;
                    }
                    //退出类时把类的左花括号减一
                }

                if(functionFlag==1)
                {//进入函数了
                    functionLine[functionResult]++;//记录函数行数
                    if (line.startsWith("//") || line.startsWith("/*") || commentFlag==true)
                    {//先把注释除去
                        if(line.startsWith("/*"))commentFlag = true;   //标注是否在注释块中
                        if(line.indexOf("*/")>=0)commentFlag = false;
                    }
                    else
                    {//不是注释
                        //先把函数里的 语句 （statement）别存到数组中
                        if(lowLine.indexOf("if")>=0 || lowLine.indexOf("else")>=0 ||
                                lowLine.indexOf("for")>=0 || lowLine.indexOf("while")>=0)
                        {
                            functionState[functionResult]++;
                            if(line.lastIndexOf(";")==line.length()-1)//像for循环里里面的条件一定含有；  所以要看左后一句是不是；
                                functionState[functionResult]++;
                        }
                        else if(
                                lowLine.indexOf("break")>=0 ||	lowLine.indexOf("continue")>=0 ||
                                        lowLine.indexOf("goto")>=0 || lowLine.indexOf("switch")>=0 ||
                                        lowLine.indexOf("case")>=0 || lowLine.indexOf("default")>=0 ||
                                        lowLine.indexOf("return")>=0 || lowLine.indexOf("catch")>=0)
                            functionState[functionResult]++;

                        else if(line.indexOf(";")>0 || lowLine.indexOf("try")>=0 || lowLine.indexOf("finally")>=0 || lowLine.indexOf("class")>=0)
                        {
                            functionState[functionResult]++;
                        }

                        //计算函数圈复杂度 用谓词节点加1的办法计算
                        if(lowLine.startsWith("for") || lowLine.startsWith("while"))
                            functionComplex[functionResult]++;

                        if(lowLine.startsWith("for") || lowLine.startsWith("while")
                                ||lowLine.startsWith("else"))
                        {
                            numDepth++;
                            if(lowLine.indexOf('{')<0)//判断这个if是不是只有一行语句
                                singleLine=true;//如果该行没有出现{ 那么就要判断下一行是否有{
                            if(numDepth>maxDepth)
                                maxDepth=numDepth;
                        }

                        if(lowLine.startsWith("if") || lowLine.startsWith("else if"))
                        {
                            functionComplex[functionResult]++;
                            numDepth++;//如果遇到分支语句 就先+1
                            if(lowLine.indexOf('{')<0)//判断这个if是不是只有一行语句
                                singleLine=true;//如果该行没有出现{ 那么就要判断下一行是否有{

                            if(numDepth>maxDepth)
                                maxDepth=numDepth;

                            int indexAnd=lowLine.indexOf("&&");
                            while(indexAnd>0)
                            {
                                functionComplex[functionResult]++;
                                indexAnd=lowLine.indexOf("&&", indexAnd+2);
                            }

                            int indexOr=lowLine.indexOf("||");
                            while(indexOr>0)
                            {
                                functionComplex[functionResult]++;
                                indexOr=lowLine.indexOf("||", indexOr+2);
                            }
                        }


                    }//不是注释
                }//函数里的判断结束

                if((line.toUpperCase().indexOf("CLASS")>=0 && line.toUpperCase().indexOf("PUBLIC")>=0) || line.toUpperCase().indexOf("STRUCT")>=0 )
                {
                    //类的判断标志暂定为class public 和 (
                    classNum++;//记录代码中类和结构体的个数
                    classLeft++;
                    //先把找到的类存到map中
                    int a=lowLine.indexOf("class")+6;
                    int endIndex=0;
                    if(lowLine.indexOf('{')>0 && lowLine.indexOf(':')<0)
                        endIndex=lowLine.indexOf('{');
                    else if(lowLine.indexOf(':')>0)
                        endIndex=lowLine.indexOf(':');
                    else endIndex=lowLine.length();

                    String str = lowLine.substring(a,endIndex);
                    System.out.println("lei   "+str);
                    cla.put(str,1);
                    //现在找的类一定是第一次出现的  后面处理继承出现的


                    if(lowLine.indexOf(':')>0)
                    {
                        //出现继承
                        int startIndex=lowLine.lastIndexOf(' ');
                        System.out.println(lowLine);
                        //防止public class xyz:public h{
                        int end2Index=lowLine.length();
                        if(lowLine.indexOf('{')>0)
                            end2Index=lowLine.indexOf('{');
                        String s=lowLine.substring(startIndex+1,end2Index);
                        System.out.println("jicheng**"+s+"**");
                        Integer times = cla.get(s)+1;
                        cla.put(str,times);
                    }

                }

                if(line.indexOf('{')>=0 && line.indexOf('}')<0)
                //后半个条件是防止单行语句中数组赋值出现{1,2,...}误判为函数
                {
                    left++;//通过花括号来判断函数个数
                    if(left==(classLeft+1))
                    {
                        if(line.indexOf('{')>1)
                            preLine=line;	//这说明{和函数声明语句写在了一行
                        functionFlag=1;//进入函数 开始计数   默认
                        numDepth=0;///每进入一个新的函数就要把深度计数器清零
                        maxDepth=0;

                        int end=preLine.indexOf('(');
                        int start=preLine.lastIndexOf(' ',end)+1;//函数起始点一定是（前的第一个空格处
                        if(start<0)start=0;//排除构造函数
                        preLine=preLine.substring(start,end)+"()";
                        funName[functionResult]=preLine;//数组存入函数名称
                    }
                }
                preLine=line;
            }
        } catch (FileNotFoundException e) {
            //System.out.println("有文件输入错误，请核对！"); //检查到文件不存在，提示错误
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


        if(functionResult!=0)
        {

            for(int i=0;i<functionResult;i++){
//		        	System.out.println("函数 "+funName[i]+" 行数："+functionLine[i]);
                res.append("函数 "+funName[i]+" 行数："+functionLine[i]+"\r\n");
                functionLineNum+=functionLine[i];
            }

//		        	System.out.println("函数平均行数："+functionLineNum/functionResult);
            res.append("函数平均行数："+functionLineNum/functionResult+"\r\n"+"\r\n");

            String maxFunComplexName="";
            int maxFunComplexLineNum=0;
            for(int i=0;i<functionResult;i++){
                functionComplex[i]++;
                if(functionComplex[i]>maxFunComplex)
                {
                    maxFunComplex=functionComplex[i];
                    maxFunComplexName=funName[i];
                    maxFunComplexLineNum=functionLine[i];
                }
                functionComplexSum+=functionComplex[i];
                res.append("函数 "+funName[i]+" 圈复杂度："+functionComplex[i]+"\r\n");
                //System.out.println("函数 "+funName[i]+" 圈复杂度："+functionComplex[i]);
            }
            res.append("最大圈复杂度函数名："+maxFunComplexName+"	行数为"+maxFunComplexLineNum+"\r\n");
            res.append("最大圈复杂度："+maxFunComplex+"\r\n");
            res.append("函数平均圈复杂度："+(float)functionComplexSum/functionResult+"\r\n"+"\r\n");
//		        System.out.println("最大圈复杂度函数名："+maxFunComplexName+"	行数为"+maxFunComplexLineNum);
//		        System.out.println("最大圈复杂度："+maxFunComplex);
//		        System.out.println("函数平均圈复杂度："+(float)functionComplexSum/functionResult);
//
//		        System.out.println();
            int maxBlockDepthSum=0, maxBlockDepthLine=0;
            String maxBlockDepthName="";
            for(int i=0;i<functionResult;i++){
                funMaxDepth[i]++;
                if(funMaxDepth[i]>maxDepth)
                {
                    maxBlockDepthName=funName[i];
                    maxDepth=funMaxDepth[i];
                    maxBlockDepthLine=functionLine[i];
                }
                maxBlockDepthSum+=funMaxDepth[i];
                res.append("函数 "+funName[i]+" 最大深度："+funMaxDepth[i]+"\r\n");
                //System.out.println("函数 "+funName[i]+" 最大深度："+funMaxDepth[i]);
            }
            res.append("最大块深度函数名："+maxBlockDepthName+"	行数为"+maxBlockDepthLine+"\r\n");
            res.append("函数深度："+maxDepth+"\r\n");
            res.append("函数平均深度："+(float)maxBlockDepthSum/functionResult+"\r\n"+"\r\n");
//		        System.out.println("最大块深度函数名："+maxBlockDepthName+"	行数为"+maxBlockDepthLine);
//		        System.out.println("函数深度："+maxDepth);
//		        System.out.println("函数平均深度："+(float)maxBlockDepthSum/functionResult);


            System.out.println();
            for(int i=0;i<functionResult;i++){
                res.append("函数 "+funName[i]+" 语句数："+functionState[i]+"\r\n");
                //System.out.println("函数 "+funName[i]+" 语句数："+functionState[i]);
                functionStateNum+=functionState[i];
            }
            res.append("函数平均语句数："+functionStateNum/functionResult+"\r\n"+"\r\n");
            //System.out.println("函数平均语句数："+functionStateNum/functionResult);
        }
        //System.out.println(cla.toString());
        res.append("\r\n"+"函数个数："+functionResult+"\r\n");
        funNum+=functionResult;
        return res;
    }//showresult

    public String lei(){
        StringBuilder res = new StringBuilder();

        CommitLineNumber commitLineNumber = new CommitLineNumber();
        Integer a = commitLineNumber.getLineNum();
        res.append("工程总行数："+a.toString()+"\r\n");
        a=commitLineNumber.getBlankNum();
        res.append("工程总空行数："+a.toString()+"\r\n");
        a=commitLineNumber.getCodeNum();
        res.append("工程总代码行数："+a.toString()+"\r\n");
        a=commitLineNumber.getCommentNum();
        res.append("工程总注释行数："+a.toString()+"\r\n");
        a=commitLineNumber.getConstAllNum();
        res.append("工程总常量数："+a.toString()+"\r\n");
        a=commitLineNumber.getStaticAllNum();
        res.append("工程总静态变量数："+a.toString()+"\r\n");

        res.append("工程中函数总个数："+funNum+"\r\n\r\n");

        res.append("工程中类的总个数："+classNum+"\r\n");
        for(String s:cla.keySet())
        {
            res.append("类名："+s+"     最大继承深度："+cla.get(s)+"\r\n");
        }
        return res.toString();
    }
}

