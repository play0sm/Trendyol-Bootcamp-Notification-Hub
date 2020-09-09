package com.kodluyoruz.trendyolbootcamp.language;

public class English implements Language {
    @Override
    public void printExceedPackageQuota() {
        System.out.println("Package quota exceed.");
    }

    @Override
    public void printNoSubscriptionFound() {
        System.out.println("Subscription not found.");

    }

    @Override
    public void printSubscriptionExist() {
        System.out.println("Subscription already exist.");

    }

    @Override
    public void printUserBlocked() {
        System.out.println("User blocked.");
    }
}
