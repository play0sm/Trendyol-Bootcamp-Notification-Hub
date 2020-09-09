package com.kodluyoruz.trendyolbootcamp.model.electronicmessage;

import com.kodluyoruz.trendyolbootcamp.model.Company;
import com.kodluyoruz.trendyolbootcamp.model.User;

import java.util.Date;
import java.util.List;

public class EmailGroup extends ElectronicMessageGroup {
    private String subject;

    public EmailGroup(Company sender, String message, Date date, List<User> receivers, String subject) {
        super(sender, message, date, receivers);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
