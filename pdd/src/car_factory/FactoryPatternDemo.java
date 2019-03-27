package car_factory;
public class FactoryPatternDemo {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();  // 定义工厂类，创建工厂对象，
        Car car1 = carFactory.getCar("smallcar");// 用这个工厂对象的方法，返回指定的汽车
        car1.drive();
        Car car2 = carFactory.getCar("bigcar");
        car2.drive();
    }
}
