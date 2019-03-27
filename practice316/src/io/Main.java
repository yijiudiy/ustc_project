package io;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedReader rd = openFileReader(sc,"input file:");
        showFileLineByLine(rd);
        closeBufferedReader(rd);
    }
    private static void showFileLineByLine(BufferedReader rd){
        try{
            while(true){
                String line = rd.readLine();
                if(line == null){
                    break;
                }
                System.out.println(line);
            }
        }catch (IOException ex){
            throw new RuntimeException(ex.toString());
        }
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
    public static void closeBufferedReader(BufferedReader rd){
        try{
            rd.close();
        }catch (IOException ex){
            throw new RuntimeException(ex.toString());
        }
    }
}
