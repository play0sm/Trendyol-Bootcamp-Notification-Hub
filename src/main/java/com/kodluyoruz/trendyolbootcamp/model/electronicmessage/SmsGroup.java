package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;
import java.util.List;

public class SmsGroup extends ElectronicMessageGroup {
    public SmsGroup(Company sender, String message, Date date, List<User> receivers) {
        super(sender, message, date, receivers);
    }
}
