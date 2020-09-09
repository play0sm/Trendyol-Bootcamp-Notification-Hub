package com.kodluyoruz.trendyolbootcamp.exception;

import java.util.Date;

public class NoSubscriptionFound extends RuntimeException {
    public NoSubscriptionFound(int month) {
        super("No subscription found at " + (month+1));
    }
}
