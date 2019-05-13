import java.text.NumberFormat;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import java.io.*;
public class CodeMeasure {
    public  StringBuffer res=new StringBuffer();
    Integer  staNum=0;
    //字符串读取
    public  String[] lsmea(String str[]) {
        //ArrayList<String> result = new ArrayList<String>();
        String[] result = new String[str.length+1];
        int i=0;

        for (String s : str) {
//            Map<String,Integer> cla = new LinkedHashMap<String,Integer>();
            //for (String s : str) {
            //res.delete(0,res.length());
            res.delete(0,res.length());//res一定要在循环开始时每次清空  不然会每次累加上次测试的结果
            FileWriter fw = null;
            File f = new File("a.txt");

            try {
                if (!f.exists()) {
                    f.createNewFile();
                }
                fw = new FileWriter(f);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(s, 0, s.length() - 1);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuffer comBuf = new StringBuffer(new CommitLineNumber(f).showResult());
            res.append(comBuf);
            //显示类个数和函数个数

            int branchResult = 0, stateRusult = 0;
            FunctionNumber fun = new FunctionNumber(f);
            res.append(fun.showResult());
            int classNum = fun.getClassNum();//读取类数和函数数 方便下面语句计算
            int funNum = fun.getFunctionNum();

            //显示语句数和分支比例
            StatementsNumber sta = new StatementsNumber(f);
            sta.showResult();
            int statementsNum = sta.getStatementsNum();
            int branchNum = sta.getBranchNum();
            statementsNum += classNum;//这里语句数要加上类和函数的声明语句
            statementsNum += funNum;

            float t = branchNum;
            float branchPercent = t / statementsNum;
            NumberFormat nt = NumberFormat.getPercentInstance();
            res.append("总语句数：" + statementsNum + "\r\n");
            staNum+=statementsNum;
            //System.out.println("总语句数："+statementsNum);

            if (statementsNum != 0)
                res.append("分支语句数：" + branchNum + "    分支语句比例" + nt.format(branchPercent) + "\r\n" + "\r\n");
            //System.out.println("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent));
            f.delete();
            //result.add(res.toString());
            result[i++]=res.toString();
            //i++;
        }
        //System.out.println("i====="+i);
        FunctionNumber func = new FunctionNumber(new File(""));
        String lei = func.lei();
        lei+="工程总语句数"+staNum.toString();
        result[i++]=lei.toString();
        return result;
    }
//        //文件读取
//        public String filemea (File f){
//            StringBuffer comBuf = new StringBuffer(new CommitLineNumber(f).showResult());
//            res.append(comBuf);
//            //显示类个数和函数个数
//
//            int branchResult = 0, stateRusult = 0;
//            FunctionNumber fun = new FunctionNumber(f);
//            res.append(fun.showResult());
//            int classNum = fun.getClassNum();//读取类数和函数数 方便下面语句计算
//            int funNum = fun.getFunctionNum();
//
//            //显示语句数和分支比例
//            StatementsNumber sta = new StatementsNumber(f);
//            sta.showResult();
//            int statementsNum = sta.getStatementsNum();
//            int branchNum = sta.getBranchNum();
//            statementsNum += classNum;//这里语句数要加上类和函数的声明语句
//            statementsNum += funNum;
//
//            float t = branchNum;
//            float branchPercent = t / statementsNum;
//            NumberFormat nt = NumberFormat.getPercentInstance();
//            res.append("总语句数：" + statementsNum + "\r\n");
//            //System.out.println("总语句数："+statementsNum);
//
//            if (statementsNum != 0)
//                res.append("分支语句数：" + branchNum + "    分支语句比例" + nt.format(branchPercent) + "\r\n" + "\r\n");
//            //System.out.println("分支语句数："+branchNum+"    分支语句比例"+nt.format(branchPercent));
//            f.delete();
//            return res.toString();
//        }
}