package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.FixedPackage;

public class SmsFixedPackage extends FixedPackage implements SmsPackage {
    public SmsFixedPackage(int quota, double price) {
        super(quota, price);
    }
}
