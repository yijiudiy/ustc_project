package second;

import java.util.*; /**  * Created by 凌 on 2019/3/10.  * 注释：字符去重后，找到首个字母最小值  */
public class First {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine(); if (str == null || str.trim().length() == 0){
        System.out.println(""); return;
    }
    str = str.toLowerCase();
    Map<Character,Integer> map = new HashMap<Character,Integer>();
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (map.containsKey(ch)){
            map.put(ch, (Integer) map.get(ch)+1);
        }else {
            map.put(ch, 1);
        }
    }
    char firstMin='z';
    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (firstMin > ch){
            firstMin =ch;
        }
        int value = map.get(ch);
        if (value == 1){
            break;
        }else{
            map.put(ch,--value);
        }
    }
    }
}