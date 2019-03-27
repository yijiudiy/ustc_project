
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Fujia{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedReader rd = openFileReader(sc,"hello");
    }
    public static BufferedReader openFileReader(Scanner sc, String prompt){
        BufferedReader rd = null;
        while(rd == null){
            try{
                System.out.println(prompt);
                String name = sc.nextLine();
                rd = new BufferedReader(new FileReader(name));
            }catch (FileNotFoundException foe){
                System.out.println( " 文件不存在！");
            }
        }
        return rd;
    }
}