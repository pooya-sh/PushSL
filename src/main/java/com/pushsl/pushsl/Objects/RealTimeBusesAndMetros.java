package com.pushsl.pushsl.Objects;

public class RealTimeBusesAndMetros {
    String TransportMode;
    String GroupOfLine;
    String DisplayTime;
    String LineNumber;
    String Destination;
    String JourneyDirection;
    String StopAreaName;
    String TimeTableDateTime;
    String ExpectedDateTime;
    String JourneyNumber;

    @Override
    public String toString() {
        return "RealTimeBusesAndMetros{" +
                "TransportMode='" + TransportMode + '\'' +
                ", GroupOfLine='" + GroupOfLine + '\'' +
                ", DisplayTime='" + DisplayTime + '\'' +
                ", LineNumber='" + LineNumber + '\'' +
                ", Destination='" + Destination + '\'' +
                ", JourneyDirection='" + JourneyDirection + '\'' +
                ", StopAreaName='" + StopAreaName + '\'' +
                ", TimeTableDateTime='" + TimeTableDateTime + '\'' +
                ", ExpectedDateTime='" + ExpectedDateTime + '\'' +
                ", JourneyNumber='" + JourneyNumber + '\'' +
                '}';
    }
}
