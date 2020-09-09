package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;

public class Email extends ElectronicMessageSingle {
    private String subject;

    public Email(Company sender, User receiver, String message, Date date, String subject) {
        super(sender, message, date, receiver);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
