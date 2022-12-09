package zsa15_lab3;

public class Microwave {

    int id;            
    String brand;  
    String model;

    public Microwave() {
    }

    public Microwave(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    
    @Override
    public String toString() {
        return String.format("Бренд = %s, Модель = %s", brand, model);
    }
}
