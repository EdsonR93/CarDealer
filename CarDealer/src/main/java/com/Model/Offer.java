package com.Model;

public class Offer {
    private final int offerId;
    private final int userId;
    private final int carSerialNum;
    private double amountOffered = 0;
    private int months = 0;
    private boolean accepted = false;
    private boolean pending = false;

    public Offer(int offerId, int userId, int carSerialNum, float amountOffered, int months, boolean accepted, boolean pending) {
        this.offerId = offerId;
        this.userId = userId;
        this.carSerialNum = carSerialNum;
        this.amountOffered = amountOffered;
        this.months = months;
        this.accepted = accepted;
        this.pending = pending;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarSerialNum() {
        return carSerialNum;
    }


    public double getAmountOffered() {
        return amountOffered;
    }

    public void setAmountOffered(float amountOffered) {
        this.amountOffered = amountOffered;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", userId=" + userId +
                ", carSerialNum=" + carSerialNum +
                ", amountOffered=" + amountOffered +
                ", months=" + months +
                ", accepted=" + accepted +
                ", pending=" + pending +
                '}';
    }
}
