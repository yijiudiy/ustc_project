package second;

import java.util.Scanner;

public class my {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            s = s.toLowerCase();
            char ch = find(s);
            System.out.println(ch);
        }
    }
    public static char find(String s){
        int [] c = new int[26];
        for(int i=0; i<s.length();i++){
            c[s.charAt(i)-'a']++;
        }
        char min = 'z';
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);//
            if(c[ch-'a']<2){
                if(ch<min)
                    return ch; // 既然ch之前的数都可以去掉，那么我就看ch能不能去掉，如果计数是1，就肯定不能去掉
            }
            else{
                if(ch<min){
                    min = ch;
                }//到这一步，min保留了当前最小字母，并且ch及其之前的所有数都可以去掉
                c[min-'a']--;
            }
        }
        return min;
    }
}