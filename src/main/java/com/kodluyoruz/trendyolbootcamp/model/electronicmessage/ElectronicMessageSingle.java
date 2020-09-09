package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;

public class ElectronicMessageSingle extends ElectronicMessage {
    private User receiver;

    public ElectronicMessageSingle(Company sender, String message, Date date, User receiver) {
        super(sender, message, date);
        this.receiver = receiver;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceivers(User receivers) {
        this.receiver = receivers;
    }
}
