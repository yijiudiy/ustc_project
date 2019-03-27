package student_factory;

public class StudentFactoryDemo {
    public static void main(String[] args) {
        StudentFactory studentFactory = new StudentFactory();
        Student stu1 = studentFactory.getStudent("primarystudent");
        Student stu2 = studentFactory.getStudent("middlestudent");
        Student stu3 = studentFactory.getStudent("collegestudent");
        stu1.sayHello();
        stu2.sayHello();
        stu3.sayHello();
    }
}
