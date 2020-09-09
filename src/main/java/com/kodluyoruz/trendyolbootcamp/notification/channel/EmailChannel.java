package com.kodluyoruz.trendyolbootcamp.notification.channel;

import com.kodluyoruz.trendyolbootcamp.exception.ExceedPackageQuota;
import com.kodluyoruz.trendyolbootcamp.exception.NoSubscriptionFound;
import com.kodluyoruz.trendyolbootcamp.exception.UserBlocked;
import com.kodluyoruz.trendyolbootcamp.model.User;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.Email;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.EmailGroup;

import java.util.List;

public class EmailChannel implements Channel<Email, EmailGroup> {

    @Override
    public void send(Email message) {
        try {
            message.getSender().useEmailSubscription(message.getDate());
            System.out.println("It was sent from the Email " + message.getSender().getName() + " user to the user " + message.getReceiver().getEmail() + " with the subject '" + message.getSubject() + " and '" + message.getMessage() + "' message.");
        } catch (ExceedPackageQuota e) {
            message.getSender().getLanguage().printExceedPackageQuota();
        } catch (NoSubscriptionFound e) {
            message.getSender().getLanguage().printNoSubscriptionFound();
        } catch (UserBlocked e) {
            message.getSender().getLanguage().printUserBlocked();
        }
    }

    @Override
    public void sendAll(EmailGroup message) {
        List<User> receivers = message.getReceivers();
        for (User receiver : receivers) {
            Email temp = new Email(message.getSender(), receiver, message.getMessage(), message.getDate(), message.getSubject());
            send(temp);
        }
    }
}
