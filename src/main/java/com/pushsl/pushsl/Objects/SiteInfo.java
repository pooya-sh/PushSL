package com.pushsl.pushsl.Objects;

public class SiteInfo {
    public String Name;
    public String SiteId;
    public String Type;
    public String X;
    public String Y;

    @Override
    public String toString() {
        return "SiteInfo{" +
                "Name='" + Name + '\'' +
                ", SiteId='" + SiteId + '\'' +
                ", Type='" + Type + '\'' +
                ", X='" + X + '\'' +
                ", Y='" + Y + '\'' +
                '}';
    }
}
