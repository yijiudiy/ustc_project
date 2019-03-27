package may;
    /*
题目1:
  字符串按以下规则自定义排序：
1.英文字母从A到Z排列，不区分大小写，如果大小写同时存在，则按照输入顺序排列。

2.非英文字母的字符保持原来的位置。

样例：
    输入：
   Today, Alibaba Group Has About 50000 Employee (2017/12/14).
    输出：
   aAaaa, AbbbdEe eGHil lmo ooopp 50000 rsTtuuyy (2017/12/14).
 */

import java.util.Scanner;

public class Main {
    //请补全代码逻辑
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        //请补全代码逻辑
        // 开始
        String r = sort(str);
        System.out.println(r);
    }

    public static String sort(String s) {
        if (s.length() <= 0) {
            return null;
        }
        char[] c = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        // 对字母进行排序的思想是：将每一字符与'a'-'z'或'A'-'Z'做比较，并添加到字符缓冲中，经过此步骤完成了
        // 不同字母按递增顺序排序，同一字母（不区分大小写）按原来顺序排列。
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < c.length; j++) {
                if (c[j] == 'a' + i || c[j] == 'A' + i) {
                    sb.append(c[j]);
                }
            }
        }
        StringBuffer res = new StringBuffer();
        int flag = 0;  //flag 用来该字母是第几个字母。
        for (int i = 0; i < c.length; i++) {
            if (isChar(c[i])) {
                res.append(sb.charAt(flag));
                flag++;
            } else {
                res.append(c[i]);
            }
        }
        return res.toString();
    }

    // 判断某一字符是否为字母
    public static boolean isChar(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }
}
