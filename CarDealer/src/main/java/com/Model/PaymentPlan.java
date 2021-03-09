package com.Model;

import java.sql.Date;

public class PaymentPlan {
    private final int planId;
    private final int userId;
    private final int carSerialNum;
    private final double monthlyPayment;
    private final int totalMonths;
    private int monthsLeft;
    private final Date purchaseDate;

    public PaymentPlan(int planId, int userId, int carSerialNum, double monthlyPayment, int totalMonths, int monthsLeft, Date purchaseDate) {
        this.planId = planId;
        this.userId = userId;
        this.carSerialNum = carSerialNum;
        this.monthlyPayment = monthlyPayment;
        this.totalMonths = totalMonths;
        this.monthsLeft = monthsLeft;
        this.purchaseDate = purchaseDate;
    }

    public int getPlanId() {
        return planId;
    }


    public int getUserId() {
        return userId;
    }


    public int getCarSerialNum() {
        return carSerialNum;
    }


    public double getMonthlyPayment() {
        return monthlyPayment;
    }


    public int getTotalMonths() {
        return totalMonths;
    }

    public int getMonthsLeft() {
        return monthsLeft;
    }

    public void setMonthsLeft(int monthsLeft) {
        this.monthsLeft = monthsLeft;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "PaymentPlan{" +
                "planId=" + planId +
                ", userId=" + userId +
                ", carSerialNum=" + carSerialNum +
                ", monthlyPayment=" + monthlyPayment +
                ", totalMonths=" + totalMonths +
                ", monthsLeft=" + monthsLeft +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
