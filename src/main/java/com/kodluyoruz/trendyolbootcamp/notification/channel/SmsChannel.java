package com.kodluyoruz.trendyolbootcamp.notification.channel;

import com.kodluyoruz.trendyolbootcamp.exception.ExceedPackageQuota;
import com.kodluyoruz.trendyolbootcamp.exception.NoSubscriptionFound;
import com.kodluyoruz.trendyolbootcamp.exception.UserBlocked;
import com.kodluyoruz.trendyolbootcamp.model.User;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Sms;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.SmsGroup;

import java.util.List;

public class SmsChannel implements Channel<Sms, SmsGroup> {

    @Override
    public void send(Sms message) {
        try {
            message.getSender().useSmsSubscription(message.getDate());
            System.out.println("It was sent from the SMS " + message.getSender().getName() + " user to the user " + message.getReceiver().getPhone() + " with the '" + message.getMessage() + "' message.");
        } catch (ExceedPackageQuota e) {
            message.getSender().getLanguage().printExceedPackageQuota();
        } catch (NoSubscriptionFound e) {
            message.getSender().getLanguage().printNoSubscriptionFound();
        } catch (UserBlocked e) {
            message.getSender().getLanguage().printUserBlocked();
        }
    }

    @Override
    public void sendAll(SmsGroup message) {
        List<User> receivers = message.getReceivers();
        for (User receiver : receivers) {
            Sms temp = new Sms(message.getSender(), receiver, message.getMessage(), message.getDate());
            send(temp);
        }
    }
}
