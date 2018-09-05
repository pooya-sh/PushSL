package com.pushsl.pushsl.Objects;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Trip {

    public List<Leg> legList;
    public String startTime;
    public String rtStartTime;
    public String endTime;
    public String rtEndTime;
    public String totalTravelTime;
    public String originName;
    public String destName;


    public Trip() {
        this.legList = new ArrayList<>();
    }

    public void setInfo() {
        this.startTime = legList.get(0).Origin.time;
        this.rtStartTime = legList.get(0).Origin.rtTime;
        this.endTime = legList.get(legList.size() - 1).Destination.time;
        this.rtEndTime = legList.get(legList.size() - 1).Destination.rtTime;

        this.originName = legList.get(0).Origin.name;
        this.destName = legList.get(legList.size() - 1).Destination.name;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime start = LocalTime.parse(this.startTime, formatter);
        LocalTime end = LocalTime.parse(this.endTime, formatter);
        Duration duration = Duration.between(start, end);

        this.totalTravelTime = duration.toMinutes() + "";
    }


    @Override
    public String toString() {
        String reslut = "";
        for (Leg l : legList) {
        reslut += l.toString();
        }
        return reslut;
    }

}
