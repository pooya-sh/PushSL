package com.pushsl.pushsl.Objects;

public class DBdata {
    public String mail;
    public String journeynumber;
    public String timebeforeleavning;
    public String date;

    @Override
    public String toString() {
        return "DBdata{" +
                "mail='" + mail + '\'' +
                ", journeynumber='" + journeynumber + '\'' +
                ", timebeforeleavning='" + timebeforeleavning + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public DBdata(String mail, String journeynumber, String timebeforeleavning, String date) {
        this.mail = mail;
        this.journeynumber = journeynumber;
        this.timebeforeleavning = timebeforeleavning;
        this.date = date;
    }
}