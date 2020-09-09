package com.kodluyoruz.trendyolbootcamp.model;

import com.kodluyoruz.trendyolbootcamp.exception.NoSubscriptionFound;
import com.kodluyoruz.trendyolbootcamp.exception.SubscriptionExist;
import com.kodluyoruz.trendyolbootcamp.language.Language;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsPackage;
import com.kodluyoruz.trendyolbootcamp.notification.SubscriptionManager;

import java.util.Date;

public class Company extends User {
    private SubscriptionManager<EmailPackage> emailSubscription;
    private SubscriptionManager<SmsPackage> smsSubscription;
    private Language language;


    public Company(String name, String email, String phone, SubscriptionManager<EmailPackage> emailSubscription, SubscriptionManager<SmsPackage> smsSubscription, Language language) {
        super(name, email, phone);
        this.emailSubscription = emailSubscription;
        this.smsSubscription = smsSubscription;
        this.language = language;
    }

    public void subscribeEmailPackage(Subscription<EmailPackage> emailPackageSubscription) {
        try {
            emailSubscription.subscribeNewPackage(emailPackageSubscription);
        } catch (SubscriptionExist e) {
            getLanguage().printSubscriptionExist();
        } catch (NoSubscriptionFound e) {
            getLanguage().printNoSubscriptionFound();
        }
    }

    public void subscribeSmsPackage(Subscription<SmsPackage> smsPackageSubscription) {
        try {
            smsSubscription.subscribeNewPackage(smsPackageSubscription);
        } catch (SubscriptionExist e) {
            language.printSubscriptionExist();
        } catch (NoSubscriptionFound e) {
            language.printNoSubscriptionFound();
        }
    }

    public void useEmailSubscription(Date date) {
        emailSubscription.useSubscription(date);
    }

    public void useSmsSubscription(Date date) {
        smsSubscription.useSubscription(date);
    }

    public SubscriptionManager<EmailPackage> getEmailSubscription() {
        return emailSubscription;
    }

    public void setEmailSubscription(SubscriptionManager<EmailPackage> emailSubscription) {
        this.emailSubscription = emailSubscription;
    }

    public SubscriptionManager<SmsPackage> getSmsSubscription() {
        return smsSubscription;
    }

    public void setSmsSubscription(SubscriptionManager<SmsPackage> smsSubscription) {
        this.smsSubscription = smsSubscription;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
