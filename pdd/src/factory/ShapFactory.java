package factory;

public class ShapFactory {
    public Shap getShap(String shapType){
        if(shapType == null){
            return null;
        }
        if(shapType.equalsIgnoreCase("circle")){
            return new Circle();
        }
        if(shapType.equalsIgnoreCase("rectangle")){
            return new Rectangle();
        }
        if(shapType.equalsIgnoreCase("square")){
            return new Square();
        }
        return null;
    }
}
