package quicksort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String ls = sc.nextLine();
            String[] s = ls.split(" ");
            int[] a = new int[s.length];
            for(int i=0; i<s.length; i++){
                a[i] = Integer.valueOf(s[i]);
            }
            quickSort(a,0,a.length-1);
            for(int i=0; i<a.length; i++){
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }
    }
    private static void quickSort(int[] a, int beg, int end){
        if(beg<end){
            int q = partition(a,beg,end);
            quickSort(a,beg,q-1);
            quickSort(a,q+1,end);
        }
    }
    private static int partition(int[] a,int beg, int end){
        int i = beg-1;
        int x = a[end];
        for(int j = beg; j<=end-1; j++){
            if(a[j] <= x){
                i++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i+1];
        a[i+1] = a[end];
        a[end] = t;
        return i+1;
    }
}
