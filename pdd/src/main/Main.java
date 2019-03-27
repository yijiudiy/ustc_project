package main;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            String[] ns = new String[n];
            for(int i=0; i<n; i++){
                ns[i] = sc.next();
            }
            System.out.println("结束一轮");
//            int m = sc.nextInt();
//            String[] ms = new String[m];
//            for(int i=0; i<m; i++){
//                ms[i] = sc.nextLine();
//            }
//
//            int count = 0;
//            boolean found = false;
//            for(int i=0; i<n; i++){
//                for(int j = 0; j<m; j++){
//                    if(ms[j].equals(ns[i])){
//                        found = true;
//                        break;
//                    }
//                }
//                if(!found){
//                    count++;
//                }else{
//                    break;
//                }
//            }
//            if(count == 0){
//                System.out.println(-1);
//            }else{
//                System.out.println(count);
//            }
        }
    }
}
