package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.FlexiblePackage;

public class SmsFlexiblePackage extends FlexiblePackage implements SmsPackage {
    public SmsFlexiblePackage(double usedPerPrice) {
        super(usedPerPrice);
    }
}
