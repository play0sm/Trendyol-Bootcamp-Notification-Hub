package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;
import java.util.List;

public abstract class ElectronicMessageGroup extends ElectronicMessage {

    private List<User> receivers;

    public ElectronicMessageGroup(Company sender, String message, Date date,List<User> receivers) {
        super(sender, message, date);
        this.receivers = receivers;
    }


    public List<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<User> receivers) {
        this.receivers = receivers;
    }

}
