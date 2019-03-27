/*package fenlei;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int pm = sc.nextInt();
            int pn = sc.nextInt();
            int count = 1;
            int cur = 2;
            int i = 1;
            while(count<=pn){
                if(isPrime(cur)){
                    if(count >= pm){
                        System.out.print(cur);
                        if(i%3 == 0){
                            System.out.print('\n');
                            i++;
                        }else{
                            if(count < pn){
                                System.out.print(" ");
                                i++;
                            }else{
                                ;
                            }
                        }

                    }
                    count++;
                    cur++;
                }else {
                    cur++;
                }
            }
        }
    }
    static boolean isPrime(int k){
        int i = 2;
        while(i * i <= k){
            if(k%i == 0){
                return false;
            }else {
                i++;
            }
        }
        return true;
    }
}*/