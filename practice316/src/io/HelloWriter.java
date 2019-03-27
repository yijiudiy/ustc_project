package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWriter {
    public void run(){
        try {
            PrintWriter wr = new PrintWriter(
                    new BufferedWriter(new FileWriter("aaa.txt")));
            wr.println("hello World!\n las kjflajlajflfadfsj");
            wr.close();
        }catch (IOException ex){
            throw new RuntimeException(ex.toString());
        }
    }

    public static void main(String[] args) {
        new HelloWriter().run();
    }
}
