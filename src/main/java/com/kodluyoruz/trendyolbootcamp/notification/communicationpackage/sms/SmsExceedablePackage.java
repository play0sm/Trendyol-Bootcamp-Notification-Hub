package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.ExceedablePackage;

public class SmsExceedablePackage extends ExceedablePackage implements SmsPackage {
    public SmsExceedablePackage(int quota, double price, CommunicationPackage overPackage) {
        super(quota, price, overPackage);
    }
}
