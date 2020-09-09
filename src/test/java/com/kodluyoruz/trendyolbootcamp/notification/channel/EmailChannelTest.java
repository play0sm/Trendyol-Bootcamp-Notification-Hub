package com.kodluyoruz.trendyolbootcamp.notification.channel;

import com.kodluyoruz.trendyolbootcamp.language.Turkish;
import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.Subscription;
import com.kodluyoruz.trendyolbootcamp.model.User;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Email;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.EmailGroup;
import com.kodluyoruz.trendyolbootcamp.notification.SubscriptionManager;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailExceedablePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailFixedPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsPackage;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailChannelTest {

    @Test
    public void it_should_send_mail() {
        // Given
        //Create Email Packages
        EmailExceedablePackage emailFixedQuota = new EmailExceedablePackage(1000, 10, new EmailFixedPackage(1000, 10));

        //Create company
        SubscriptionManager<EmailPackage> emailSubscription = new SubscriptionManager<>(2);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        Company company = new Company("Akif", "mehmetakif.9825@gmail.com", "05350833380", emailSubscription, smsSubscription, new Turkish());

        //Create user
        User user = new User("Akif", "mehmetakif.9825@gmail.com", "05350833380");

        //Create Channels
        EmailChannel emailChannel = new EmailChannel();

        // When
        Subscription<EmailPackage> emailPackageSubscription = new Subscription<>(emailFixedQuota, new Date());
        company.subscribeEmailPackage(emailPackageSubscription);


        Email email = new Email(company, user, "Message", new Date(), "Subject");
        emailChannel.send(email);

        // Then
        assertThat(((CommunicationPackage) company.getEmailSubscription().getUnpaidSubscription(new Date().getMonth()).getSubscribedPackage()).getUsedAmount()).isEqualTo(1);
    }

    @Test
    public void it_should_send_mail_multi_user() {
        // Given
        //Create Email Packages
        EmailExceedablePackage emailFixedQuota = new EmailExceedablePackage(1000, 10, new EmailFixedPackage(1000, 10));

        //Create company
        SubscriptionManager<EmailPackage> emailSubscription = new SubscriptionManager<>(2);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        Company company = new Company("Akif", "mehmetakif.9825@gmail.com", "05350833380", emailSubscription, smsSubscription, new Turkish());

        //Create users
        User user = new User("Akif", "mehmetakif.9825@gmail.com", "05350833380");
        User user2 = new User("Mehmet", "test@gmail.com", "05555555555");


        //Create Channels
        EmailChannel emailChannel = new EmailChannel();

        // When
        Subscription<EmailPackage> emailPackageSubscription = new Subscription<>(emailFixedQuota, new Date());
        company.subscribeEmailPackage(emailPackageSubscription);


        EmailGroup email = new EmailGroup(company, "Message", new Date(), new ArrayList<User>(Arrays.asList(user, user2)),
                "Subject");
        emailChannel.sendAll(email);

        // Then
        assertThat(((CommunicationPackage) company.getEmailSubscription().getUnpaidSubscription(new Date().getMonth()).getSubscribedPackage()).getUsedAmount()).isEqualTo(2);
    }

}