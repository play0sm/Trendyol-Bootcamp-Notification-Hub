package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

public class FlexiblePackage extends CommunicationPackage {
    private double pricePerUse;

    public FlexiblePackage(double pricePerUse) {
        super();
        if (pricePerUse <= 0) {
            throw new IllegalArgumentException("pricePerUse can not equal or smaller than 0.");
        }
        this.pricePerUse = pricePerUse;
    }

    @Override
    public void use() {
        increaseUsedAmount();
    }

    @Override
    public double getPrice() {
        return pricePerUse * getUsedAmount();
    }

    public double getPricePerUse() {
        return pricePerUse;
    }

    public void setPricePerUse(double pricePerUse) {
        this.pricePerUse = pricePerUse;
    }
}
