package io;

import javax.swing.*;
import java.io.*;

public class CountLines {
    public void run(){
        try {
            BufferedReader rd = openFileReaderUsingDialog();
            String maxl = "";
            if (rd != null) {
                while(true){
                    String s = rd.readLine();
                    if(s == null) break;
                    if(s.length()>maxl.length()){
                        maxl = s;
                    }
                }
                rd.close();
            }
            System.out.println("最长的行：" + maxl);
        }catch (IOException ex){
            throw new RuntimeException(ex.toString());
        }
    }
    private BufferedReader openFileReaderUsingDialog()throws IOException{
        File dir = new File(System.getProperty("user.dir")); // 一个初始目录
        JFileChooser chooser = new JFileChooser(dir);       // 一个选择器窗口
        int result = chooser.showOpenDialog(null);  // 调用选择器的方法（鼠标选中，null表示初始状态）
        if(result == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();    // 调用选择器的方法，创建文件对象
            return new BufferedReader(new FileReader(file)); // 利用该文件对象创建BufferedReader对象并返回
        }
        return null;
    }

    public static void main(String[] args) {
        new CountLines().run();
    }
}
