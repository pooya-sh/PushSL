package com.pushsl.pushsl.Objects;

public class SiteInfo {
    public String Name;
    public String SiteId;
    public String Type;

    @Override
    public String toString() {
        return "SiteInfo{" +
                "Name='" + Name + '\'' +
                ", SiteId='" + SiteId + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}