package com.pushsl.pushsl.Objects;

public class Destination {
    public String name;
    public String time;
    public String date;
    public String rtTime;
    public String rtDate;

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", rtTime='" + rtTime + '\'' +
                ", rtDate='" + rtDate + '\'' +
                '}';
    }
}