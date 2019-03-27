package car_factory;

public class CarFactory {
    public Car getCar(String carType){
        if(carType == null){
            return null;
        }
        if(carType.equalsIgnoreCase("smallcar")){
            return new SmallCar();
        }
        if(carType.equalsIgnoreCase("bigcar")){
            return new BigCar();
        }

        return null;
    }
}
