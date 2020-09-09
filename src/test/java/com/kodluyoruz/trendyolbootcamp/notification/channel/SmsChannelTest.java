package com.kodluyoruz.trendyolbootcamp.notification.channel;

import com.kodluyoruz.trendyolbootcamp.language.Turkish;
import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.Subscription;
import com.kodluyoruz.trendyolbootcamp.model.User;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Sms;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.SmsGroup;
import com.kodluyoruz.trendyolbootcamp.notification.SubscriptionManager;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsExceedablePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsFixedPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsFlexiblePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsPackage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SmsChannelTest {

    @Test
    void send() {
        // Given
        //Create SMS Package
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));

        //Create company
        SubscriptionManager<EmailPackage> emailSubscription = new SubscriptionManager<>(2);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        Company company = new Company("Akif", "mehmetakif.9825@gmail.com", "05350833380", emailSubscription, smsSubscription, new Turkish());

        //Create user
        User user = new User("Akif", "mehmetakif.9825@gmail.com", "05350833380");

        //Create Channels
        SmsChannel smsChannel = new SmsChannel();

        //When
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, new Date());
        company.subscribeSmsPackage(smsPackageSubscription);


        Sms sms = new Sms(company, user, "Deneme", new Date());
        smsChannel.send(sms);

        // Then
        assertThat(((CommunicationPackage) company.getSmsSubscription().getUnpaidSubscription(new Date().getMonth()).getSubscribedPackage()).getUsedAmount()).isEqualTo(1);
    }

    @Test
    void sendAll() {
        // Given
        //Create SMS Package
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));

        //Create company
        SubscriptionManager<EmailPackage> emailSubscription = new SubscriptionManager<>(2);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        Company company = new Company("Akif", "mehmetakif.9825@gmail.com", "05350833380", emailSubscription, smsSubscription, new Turkish());

        //Create user
        User user = new User("Akif", "mehmetakif.9825@gmail.com", "05350833380");
        User user2 = new User("Mehmet", "test@gmail.com", "05555555555");


        //Create Channels
        SmsChannel smsChannel = new SmsChannel();

        //When
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, new Date());
        company.subscribeSmsPackage(smsPackageSubscription);


        SmsGroup sms = new SmsGroup(company, "Deneme", new Date(), new ArrayList<User>(Arrays.asList(user, user2)));
        smsChannel.sendAll(sms);

        // Then
        assertThat(((CommunicationPackage) company.getSmsSubscription().getUnpaidSubscription(new Date().getMonth()).getSubscribedPackage()).getUsedAmount()).isEqualTo(2);
    }
}