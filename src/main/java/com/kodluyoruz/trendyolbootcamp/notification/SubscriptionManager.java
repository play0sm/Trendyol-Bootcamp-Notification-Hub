package com.kodluyoruz.trendyolbootcamp.notification;

import com.kodluyoruz.trendyolbootcamp.exception.NoSubscriptionFound;
import com.kodluyoruz.trendyolbootcamp.exception.SubscriptionExist;
import com.kodluyoruz.trendyolbootcamp.exception.UserBlocked;
import com.kodluyoruz.trendyolbootcamp.model.Subscription;
import com.kodluyoruz.trendyolbootcamp.notification.communicationpackage.CommunicationPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionManager<T> {
    private List<Subscription<T>> unPaidSubscribedPackages;
    private List<Subscription<T>> paidSubscribedPackages;
    private int userBlockingMonthLimit;

    public SubscriptionManager(int userBlockingMonthLimit) {
        if (userBlockingMonthLimit <= 0) {
            throw new IllegalArgumentException("userBlockingMonthLimit can not equal or smaller than 0.");
        }
        this.userBlockingMonthLimit = userBlockingMonthLimit;
        unPaidSubscribedPackages = new ArrayList<>();
        paidSubscribedPackages = new ArrayList<>();
    }

    public void subscribeNewPackage(Subscription<T> subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("subscription can not be null.");
        }
        if (!isExistSubscription(subscription.getDate().getMonth())) {
            unPaidSubscribedPackages.add(subscription);
        } else {
            throw new SubscriptionExist(subscription.getDate().getMonth());
        }
    }

    public boolean isExistSubscription(int month) {
        for (Subscription<T> item : unPaidSubscribedPackages) {
            if (item.getDate().getMonth() == month) {
                return true;
            }
        }
        return false;
    }

    public void paySubscription(int month) {
        Subscription<T> subscription = getUnpaidSubscription(month);
        paidSubscribedPackages.add(subscription);
        unPaidSubscribedPackages.remove(subscription);
    }

    public void useSubscription(Date date) {
        if (!isBlocked(date)) {
            Subscription<T> subscription = getUnpaidSubscription(date.getMonth());
            CommunicationPackage subscribedPackage = (CommunicationPackage) subscription.getSubscribedPackage();
            subscribedPackage.use();
        } else {
            throw new UserBlocked(userBlockingMonthLimit);
        }
    }

    public Subscription<T> getUnpaidSubscription(int month) {
        for (Subscription<T> item : unPaidSubscribedPackages) {
            if (item.getDate().getMonth() == month) {
                return item;
            }
        }
        throw new NoSubscriptionFound(month);
    }

    public Subscription<T> getPaidSubscription(int month) {
        for (Subscription<T> item : paidSubscribedPackages) {
            if (item.getDate().getMonth() == month) {
                return item;
            }
        }
        throw new NoSubscriptionFound(month);
    }

    public boolean isBlocked(Date date) {
        for (Subscription<T> item : unPaidSubscribedPackages) {
            System.out.println(diffToDay(diffBetweenDates(date, item.getDate())) > (30 * userBlockingMonthLimit));
            if (diffToDay(diffBetweenDates(date, item.getDate())) > (30 * userBlockingMonthLimit)) {
                return true;
            }
        }
        return false;
    }

    private long diffBetweenDates(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime());
    }

    private float diffToDay(long diff) {
        return diff / (1000 * 60 * 60 * 24);
    }

    public List<Subscription<T>> getUnPaidSubscribedPackages() {
        return unPaidSubscribedPackages;
    }

    public List<Subscription<T>> getPaidSubscribedPackages() {
        return paidSubscribedPackages;
    }

}
