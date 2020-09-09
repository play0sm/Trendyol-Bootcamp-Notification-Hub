package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.FixedPackage;

public class EmailFixedPackage extends FixedPackage implements EmailPackage {
    public EmailFixedPackage(int quota, double price) {
        super(quota, price);
    }
}
