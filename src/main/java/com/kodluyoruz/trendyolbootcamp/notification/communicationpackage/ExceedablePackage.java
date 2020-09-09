package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

public class ExceedablePackage extends FixedPackage {
    private CommunicationPackage overPackage;

    public ExceedablePackage(int quota, double price, CommunicationPackage overPackage) {
        super(quota, price);
        if (overPackage != null) {
            this.overPackage = overPackage;
        } else {
            throw new IllegalArgumentException("Over Package can not be null.");
        }
    }

    @Override
    public void use() {
        if (!isExceed()) {
            increaseUsedAmount();
        } else {
            overPackage.use();
        }
    }

    @Override
    public double getPrice() {
        if (isExceed() && overPackage.getUsedAmount() > 0) {
            return super.getPrice() + overPackage.getPrice();
        }
        return super.getPrice();
    }

    public CommunicationPackage getOverPackage() {
        return overPackage;
    }

    public void setOverPackage(CommunicationPackage overPackage) {
        this.overPackage = overPackage;
    }
}
