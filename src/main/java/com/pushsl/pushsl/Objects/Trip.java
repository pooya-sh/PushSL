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
    public String duration;
    public String hours;
    public String minutes;


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

        if(duration.contains("H")) {
            hours = duration.substring(duration.indexOf("T") + 1 , duration.indexOf("H"));
            minutes = duration.substring(duration.indexOf("H") + 1 , duration.indexOf("M"));
        } else {
            hours = "";
            minutes = duration.substring(duration.indexOf("T") + 1 , duration.indexOf("M"));
        }

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
