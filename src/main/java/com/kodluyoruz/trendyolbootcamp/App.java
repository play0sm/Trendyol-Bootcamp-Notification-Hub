package com.kodluyoruz.trendyolbootcamp;


import com.kodluyoruz.trendyolbootcamp.language.English;
import com.kodluyoruz.trendyolbootcamp.language.Turkish;
import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.Subscription;
import com.kodluyoruz.trendyolbootcamp.model.User;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Email;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Sms;
import com.kodluyoruz.trendyolbootcamp.notification.SubscriptionManager;
import com.kodluyoruz.trendyolbootcamp.notification.channel.EmailChannel;
import com.kodluyoruz.trendyolbootcamp.notification.channel.SmsChannel;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailExceedablePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailFixedPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailFlexiblePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsExceedablePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsFixedPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsFlexiblePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsPackage;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        //Create SMS Packages
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        SmsExceedablePackage smsFlexibleQuota = new SmsExceedablePackage(2000, 30, new SmsFlexiblePackage(0.1));

        //Create Email Packages
        EmailExceedablePackage emailFixedQuota = new EmailExceedablePackage(1000, 10, new EmailFixedPackage(1000, 10));
        EmailExceedablePackage emailFlexibleQuota = new EmailExceedablePackage(2000, 7.5, new EmailFlexiblePackage(0.03));

        //Create company
        SubscriptionManager<EmailPackage> emailSubscription = new SubscriptionManager<>(2);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        Company company = new Company("Akif", "mehmetakif.9825@gmail.com", "05350833380", emailSubscription, smsSubscription,new Turkish());

        //Create users
        User user = new User("Akif", "mehmetakif.9825@gmail.com", "05350833380");
        User user2 = new User("Mehmet", "test@gmail.com", "05555555555");

        //Create Channels
        EmailChannel emailChannel = new EmailChannel();
        SmsChannel smsChannel = new SmsChannel();


        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, new Date());
        company.subscribeSmsPackage(smsPackageSubscription);


        Sms sms = new Sms(company, user, "Deneme", new Date());
        smsChannel.send(sms);

    }
}
