package com.pushsl.pushsl.Objects;

import java.util.ArrayList;
import java.util.List;

public class Trip {

    public List<Leg> legList;

    public Trip() {
        this.legList = new ArrayList<>();
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
