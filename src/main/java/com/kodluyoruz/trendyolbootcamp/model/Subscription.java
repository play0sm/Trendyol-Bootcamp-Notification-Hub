package com.kodluyoruz.trendyolbootcamp.model;


import java.util.Date;

public class Subscription<T> {
    private T subscribedPackage;
    private Date date;

    public Subscription(T subscribedPackage, Date date) {
        this.subscribedPackage = subscribedPackage;
        this.date = date;
    }

    public T getSubscribedPackage() {
        return subscribedPackage;
    }

    public void setSubscribedPackage(T subscribedPackage) {
        this.subscribedPackage = subscribedPackage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
