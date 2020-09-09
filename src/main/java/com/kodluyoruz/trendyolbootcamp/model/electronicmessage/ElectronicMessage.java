package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;

import java.util.Date;

public abstract class ElectronicMessage {
    private Company sender;
    private String message;
    private Date date;

    public ElectronicMessage(Company sender, String message, Date date) {
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public Company getSender() {
        return sender;
    }

    public void setSender(Company sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
