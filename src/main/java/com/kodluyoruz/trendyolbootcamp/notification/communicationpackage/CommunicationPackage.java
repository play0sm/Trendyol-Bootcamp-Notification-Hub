package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

public abstract class CommunicationPackage {
    private int usedAmount;

    public CommunicationPackage() {
        this.usedAmount = 0;
    }

    public abstract void use();

    public abstract double getPrice();

    public int getUsedAmount() {
        return usedAmount;
    }

    public void increaseUsedAmount() {
        usedAmount++;
    }

}
