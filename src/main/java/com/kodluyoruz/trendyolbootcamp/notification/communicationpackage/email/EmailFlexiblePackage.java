package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email;

import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.FlexiblePackage;

public class EmailFlexiblePackage extends FlexiblePackage implements EmailPackage {
    public EmailFlexiblePackage( double usedPerPrice) {
        super(usedPerPrice);
    }

}
