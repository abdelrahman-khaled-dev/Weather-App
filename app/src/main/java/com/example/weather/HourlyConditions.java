package com.example.weather;

public class HourlyConditions {
    private String hour;
    private String temp;
    private int icon;

    public HourlyConditions(String hour, String temp) {
        this.hour = hour;
        this.temp = temp;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
