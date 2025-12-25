package com.example.weather;

public class Wind {
    private double speed;
    private int deg;

    public Wind(double speed, int deg) {
        this.deg = deg;
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
