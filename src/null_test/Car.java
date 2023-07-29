package null_test;

import java.awt.*;
import java.util.Objects;

public class Car {
    private final String name;
    private final Color color;
    public Car(String name, Color color) {
        if (name == null) {
            throw new NullPointerException("Имя автомобиля не может быть null");
        }
        if (color == null) {
            throw new NullPointerException("Цвет автомобиля не может быть null");
        }
        this.name = name;
        this.color = color;
    }
    public void assignDriver(String license, Point location) {
        if (license == null) {
            throw new NullPointerException("Лицензия не может быть null");
        }
        if (location == null) {
            throw new NullPointerException("Местоположение не может быть null");
        }
    }

//    public Car(String name, Color color) {
//        this.name = Objects.requireNonNull(name,
//                "Имя автомобиля не может быть null");
//        this.color = Objects.requireNonNull(color,
//                "Цвет автомобиля не может быть null");
//    }
//    public void assignDriver(String license, Point location) {
//        Objects.requireNonNull(license, "Лицензия не может быть null");
//        Objects.requireNonNull(location, "Местоположение не может быть null");
//    }


//    public Car(String name, Color color) {
//        if (name == null) {
//            this.name = "Безымянный";
//        } else {
//            this.name = name;
//        }
//        if (color == null) {
//            this.color = new Color(0, 0, 0);
//        } else {
//            this.color = color;
//        }
//    }

//    public Car(String name, Color color) {
//        this.name = Objects.requireNonNullElse(name, "No name");
//        this.color = Objects.requireNonNullElseGet(color,
//                () -> new Color(0, 0, 0));
//    }


}
