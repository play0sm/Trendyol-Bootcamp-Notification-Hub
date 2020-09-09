package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;

public class Sms extends ElectronicMessageSingle {
    public Sms(Company sender, User receiver, String message, Date date) {
        super(sender, message, date, receiver);
    }
}
