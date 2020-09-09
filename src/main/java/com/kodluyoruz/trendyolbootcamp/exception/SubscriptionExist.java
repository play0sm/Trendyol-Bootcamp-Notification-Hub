package com.kodluyoruz.trendyolbootcamp.exception;

public class SubscriptionExist extends RuntimeException {
    public SubscriptionExist(int month) {
        super("Subscription exist at "+month+1);
    }
}
