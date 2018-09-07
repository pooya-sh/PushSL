package com.pushsl.pushsl.Objects;

public class Leg {
    public Origin Origin;
    public Destination Destination;
    public String category;
    public String number;

    @Override
    public String toString() {
        return "Leg{" +
                "Origin=" + Origin +
                ", Destination=" + Destination +
                ", category='" + category + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

