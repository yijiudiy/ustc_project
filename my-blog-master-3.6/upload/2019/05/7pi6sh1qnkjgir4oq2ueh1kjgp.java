package calenderdemo;

import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,9);
        printNYR(c);
    }
    public static void printNYR(Calendar c){
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH );
        int day = c.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + month+1 + "月" + day + "日");
    }
}
