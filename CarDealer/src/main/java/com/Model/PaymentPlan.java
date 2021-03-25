package com.Model;

import ORM.Anotations.Column;
import ORM.Anotations.ColumnNotRequired;
import ORM.Anotations.Table;

import java.sql.Date;

@Table(name = "payment_plan")
public class PaymentPlan {

    @ColumnNotRequired
    @Column(name = "plan_id")
    private final int planId;

    @Column(name = "user_id")
    private final int userId;

    @Column(name = "car_serial_num")
    private final int carSerialNum;

    @Column(name = "monthly_payment")
    private final double monthlyPayment;

    @Column(name = "total_months")
    private final int totalMonths;

    @Column(name = "months_left")
    private int monthsLeft;

    @ColumnNotRequired
    @Column(name = "purchase_date")
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
