package com.Model;

import java.sql.Date;

public class Payment {
    private final int paymentId;
    private final int userId;
    private final int carSerialNum;
    private final double paymentAmount;
    private final Date paymentDate;

    public Payment(int paymentId, int userId, int carSerialNum, double paymentAmount, Date paymentDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.carSerialNum = carSerialNum;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarSerialNum() {
        return carSerialNum;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", carId=" + carSerialNum +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
