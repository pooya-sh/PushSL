package com.pushsl.pushsl.Objects;

public class Origin {
    public String name;
    public String time;
    public String date;
    public String rtTime;
    public String rtDate;

    @Override
    public String toString() {
        return "Origin{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", rtTime='" + rtTime + '\'' +
                ", rtDate='" + rtDate + '\'' +
                '}';
    }
}