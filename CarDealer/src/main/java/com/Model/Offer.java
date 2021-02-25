package com.Model;

public class Offer {
    private User u;
    private Car c;

    public Offer(User u, Car c) {
        this.u = u;
        this.c = c;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }
}
