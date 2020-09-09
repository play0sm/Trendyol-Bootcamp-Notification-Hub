package com.kodluyoruz.trendyolbootcamp.notification.channel;

import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.ElectronicMessageGroup;
import com.kodluyoruz.trendyolbootcamp.model.electronicmessage.ElectronicMessageSingle;

public interface Channel<T extends ElectronicMessageSingle,A extends ElectronicMessageGroup> {
     void send(T message);
     void sendAll(A message);

}
