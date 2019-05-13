package kill;

public class TestDemo {
    public static void main(String[] args) {
        killPerson(new Person("zhangsan", 33));
        System.out.println(getPerson().name + "shengchulai l ");
    }
    public static void killPerson(Person p){
        System.out.println("去死吧"+ p.name);
    }
    public static Person getPerson(){
        Person pp = new Person("baby",0);
        return pp;
    }
}
