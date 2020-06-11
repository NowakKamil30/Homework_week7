package pl.akademiaspringa.homework_week7.models;


public class Car {
    private Long id;
    private String mark;
    private String model;
    private String color;
    private String year;

    public Car(long id, String mark, String model, String color, String year) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public Car(String mark, String model, String color, String year) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }
}
