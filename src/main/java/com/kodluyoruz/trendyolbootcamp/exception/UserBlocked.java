package com.kodluyoruz.trendyolbootcamp.exception;

public class UserBlocked extends RuntimeException {
    public UserBlocked(int userBlockingMonthLimit) {
        super("You have " + userBlockingMonthLimit + "+ months of accumulated debt.");
    }
}
