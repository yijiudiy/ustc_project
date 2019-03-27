import java.util.Scanner;
public class 全排列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] c = s.toCharArray();
        print(c,c.length-1);
    }
    private static void print(char[] c,int n){
        if(0 == n){
            for(int i=0; i<c.length; i++){
                System.out.print(c[i]);
            }
            System.out.println();
        }else {
            for(int i=0; i<=n ; i++){
                swapc(c,i,n);
                print(c,n-1);
                swapc(c,i,n);
            }
        }
    }
    private static void swapc(char[] c, int i ,int j){
        char ch = c[i];
        c[i] = c[j];
        c[j] = ch;
    }
}
