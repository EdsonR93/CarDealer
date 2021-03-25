package com.Model;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;

@Table(name = "Offers")
public class Offer {
    @ColumnNotRequired
    @Column(name = "offer_id")
    private final int offerId;

    @Column(name = "user_id")
    private final int userId;

    @Column(name = "car_serial_num")
    private final int carSerialNum;

    @Column(name = "offer")
    private double amountOffered = 0;

    @Column(name = "months")
    private int months = 0;

    @ColumnNotRequired
    @Column(name = "accepted")
    private boolean accepted = false;

    @ColumnNotRequired
    @Column(name = "pending")
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

        return String.format("%10s\t%10s\t%14s\t%14s\t%10s\t%10s\t%10s",offerId,userId,carSerialNum,amountOffered,months,accepted,pending);
    }
}
