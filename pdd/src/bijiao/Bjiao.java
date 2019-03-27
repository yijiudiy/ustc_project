package bijiao;

import java.util.*;

class Student{
    public String name;
    public int score;
    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }
}

public class Bjiao {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int numPeople = sc.nextInt();
            int option = sc.nextInt();

            List<Student> stuList = new ArrayList<>();
            for(int i = 0; i<numPeople; i++){
                stuList.add(new Student(sc.next(), sc.nextInt()));
            }
            if(option == 0){
                Collections.sort(stuList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.score - o1.score;
                    }
                });
            }
            if(option == 1){
                Collections.sort(stuList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2){
                        return o1.score - o2.score;
                    }
                });
            }
            for(int i=0; i<stuList.size(); i++){
                System.out.println(stuList.get(i).name + " " + stuList.get(i).score);
            }
        }
    }
}
