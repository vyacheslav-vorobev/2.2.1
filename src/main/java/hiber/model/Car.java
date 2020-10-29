package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private int series;
    private String model = "unknown";

    public Car(){

    }
    public Car(String model, int series){
        this.series = series;
        this.model = model;
    }

    public String getModel() {
        return model;
    }
    public int getSeries() {
        return series;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return model;
    }
}
