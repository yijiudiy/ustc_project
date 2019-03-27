package student_factory;

public class StudentFactory {
    public Student getStudent(String stuType){
        if(stuType == null){
            return null;
        }
        if(stuType.equalsIgnoreCase("collegestudent")){
            return new CollegeStudent();
        }
        if(stuType.equalsIgnoreCase("primarystudent")){
            return new PrimaryStudent();
        }
        if(stuType.equalsIgnoreCase("middlestudent")){
            return new MiddleStudent();
        }
        return null;
    }
}
