package com.Model;

public class Offer {
    private final int offerId;
    private final String userId;
    private final String carId;
    private float amountOffered = 0;

    public Offer(int offerId, String userId, String carId, float amountOffered) {
        this.offerId = offerId;
        this.userId = userId;
        this.carId = carId;
        this.amountOffered = amountOffered;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCarId() {
        return carId;
    }


    public float getAmountOffered() {
        return amountOffered;
    }

    public void setAmountOffered(float amountOffered) {
        this.amountOffered = amountOffered;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId='" + offerId + '\'' +
                ", userId='" + userId + '\'' +
                ", carId='" + carId + '\'' +
                ", amountOffered=" + amountOffered +
                '}';
    }
}
