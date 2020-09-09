package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.ExceedablePackage;

public class EmailExceedablePackage extends ExceedablePackage implements EmailPackage {
    public EmailExceedablePackage(int quota, double price, CommunicationPackage overPackage) {
        super( quota, price, overPackage);
    }
}
