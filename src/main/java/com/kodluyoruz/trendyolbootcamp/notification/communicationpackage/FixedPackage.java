package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;


import com.kodluyoruz.trendyolbootcamp.exception.ExceedPackageQuota;

public class FixedPackage extends CommunicationPackage {
    private int quota;
    private double price;

    public FixedPackage(int quota, double price) {
        super();
        if (quota <= 0) {
            throw new IllegalArgumentException("quota can not equal or smaller than 0.");
        }
        if (price <=0) {
            throw new IllegalArgumentException("userBlockingMonthLimit can not equal or smaller than 0.");
        }
        this.quota = quota;
        this.price = price;
    }

    @Override
    public void use() {
        if (!isExceed()) {
            increaseUsedAmount();
        } else {
            throw new ExceedPackageQuota();
        }
    }

    public int getQuota() {
        return quota;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isExceed() {
        return getUsedAmount() == quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
