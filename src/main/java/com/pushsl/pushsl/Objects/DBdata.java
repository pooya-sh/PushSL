package com.pushsl.pushsl.Objects;

public class DBdata {
    public String mail;
    public String journeynumber;
    public String timebeforeleaving;
    public String date;

    @Override
    public String toString() {
        return "DBdata{" +
                "mail='" + mail + '\'' +
                ", journeynumber='" + journeynumber + '\'' +
                ", timebeforeleavning='" + timebeforeleaving + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public DBdata(String mail, String journeynumber, String timebeforeleaving, String date) {
        this.mail = mail;
        this.journeynumber = journeynumber;
        this.timebeforeleaving = timebeforeleaving;
        this.date = date;
    }
}