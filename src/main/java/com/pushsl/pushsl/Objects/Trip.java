package com.pushsl.pushsl.Objects;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    @Override
    public String toString() {
        String reslut = "";
        for (Leg l : legList) {
        reslut += l.toString();
        }
        return reslut;
    }

    public Trip() {
        this.legList = new ArrayList<>();
    }

    public List<Leg> legList;

}
