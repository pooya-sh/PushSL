package com.pushsl.pushsl.Objects;

public class Leg {
    public Origin Origin;
    public Destination Destination;
    public String category;
    public String number;
    public String name;
    public String direction;

    @Override
    public String toString() {
        return "Leg{" +
                "Origin=" + Origin +
                ", Destination=" + Destination +
                ", category='" + category + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}