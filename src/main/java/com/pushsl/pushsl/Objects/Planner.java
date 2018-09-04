package com.pushsl.pushsl.Objects;

public class Planner {
        public int orginID;
        public int destID;
        public String Type;
        public String X;
        public String Y;

        @Override
        public String toString() {
            return "SiteInfo{" +
                    "Name='" + orginID + '\'' +
                    ", SiteId='" + destID + '\'' +
                    ", Type='" + Type + '\'' +
                    ", X='" + X + '\'' +
                    ", Y='" + Y + '\'' +
                    '}';
        }
    }