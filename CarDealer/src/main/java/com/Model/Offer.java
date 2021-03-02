package com.Model;

public class Offer {
    private User u;
    private Car c;
    private float amountOffered = 0;

    public Offer(User u, Car c, float ao) {
        this.u = u;
        this.c = c;
        this.amountOffered = ao;
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

    public float getAmountOffered() {
        return amountOffered;
    }

    public void setAmountOffered(float amountOffered) {
        this.amountOffered = amountOffered;
    }
}
