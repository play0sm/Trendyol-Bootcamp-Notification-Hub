package com.kodluyoruz.trendyolbootcamp.notification;

import com.kodluyoruz.trendyolbootcamp.exception.SubscriptionExist;
import com.kodluyoruz.trendyolbootcamp.exception.UserBlocked;
import com.kodluyoruz.trendyolbootcamp.model.Subscription;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.email.EmailPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsExceedablePackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsFixedPackage;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.sms.SmsPackage;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionManagerTest {
    @Test
    void it_should_give_exception_when_user_block_limit_zero() {
        // Given

        // When
        boolean isIllegal = false;
        try {
            new SubscriptionManager<>(0);
        } catch (IllegalArgumentException e) {
            isIllegal = true;
        }

        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_when_user_block_limit_smaller_than_zero() {
        // Given

        // When
        boolean isIllegal = false;
        try {
            new SubscriptionManager<>(-1);
        } catch (IllegalArgumentException e) {
            isIllegal = true;
        }

        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_add_new_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);
        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);

        // Then
        assertThat(smsSubscription.getUnPaidSubscribedPackages().size()).isEqualTo(1);
    }

    @Test
    void it_should_not_add_exist_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        SmsExceedablePackage smsFixedQuota2 = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        Subscription<SmsPackage> smsPackageSubscription2 = new Subscription<>(smsFixedQuota2, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        boolean isExist = false;
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        try {
            smsSubscription.subscribeNewPackage(smsPackageSubscription2);
        } catch (SubscriptionExist e) {
            isExist = true;
        }

        // Then
        assertThat(isExist).isEqualTo(true);
    }

    @Test
    void it_should_return_true_exist_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        boolean isExist = smsSubscription.isExistSubscription(date.getMonth());

        // Then
        assertThat(isExist).isEqualTo(true);
    }

    @Test
    void it_should_pay_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        smsSubscription.paySubscription(date.getMonth());

        // Then
        assertThat(smsSubscription.getUnPaidSubscribedPackages().size()).isEqualTo(0);
        assertThat(smsSubscription.getPaidSubscribedPackages().size()).isEqualTo(1);
    }

    @Test
    void it_should_get_unpaid_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        Subscription<SmsPackage> unPaidSubscription = smsSubscription.getUnpaidSubscription(date.getMonth());

        // Then
        assertThat(unPaidSubscription).isNotEqualTo(null);
    }

    @Test
    void it_should_get_paid_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        smsSubscription.paySubscription(date.getMonth());
        Subscription<SmsPackage> paidSubscription = smsSubscription.getPaidSubscription(date.getMonth());


        // Then
        assertThat(paidSubscription).isNotEqualTo(null);
    }

    @Test
    void it_should_use_subscription() {
        // Given
        Date date = new Date();
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);
        smsSubscription.useSubscription(date);

        // Then
        assertThat(((CommunicationPackage) smsSubscription.getUnpaidSubscription(date.getMonth()).getSubscribedPackage()).getUsedAmount()).isEqualTo(1);
    }

    @Test
    void it_should_return_true_blocked_user() throws ParseException {
        // Given
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = format.parse("2020/03/01 10:10:10");
        SmsExceedablePackage smsFixedQuota = new SmsExceedablePackage(1000, 20, new SmsFixedPackage(1000, 20));
        Subscription<SmsPackage> smsPackageSubscription = new Subscription<>(smsFixedQuota, date);
        SubscriptionManager<SmsPackage> smsSubscription = new SubscriptionManager<>(2);

        // When
        smsSubscription.subscribeNewPackage(smsPackageSubscription);

        // Then
        assertThat(smsSubscription.isBlocked(new Date())).isEqualTo(true);
    }
}