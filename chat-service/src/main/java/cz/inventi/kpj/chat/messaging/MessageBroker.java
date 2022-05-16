package cz.inventi.kpj.chat.messaging;

import cz.inventi.kpj.chat.model.MessageEvent;
import cz.inventi.kpj.openapi.model.MessageRequest;

public interface MessageBroker {
    void publish(MessageEvent messageEvent);
    void registerListener(MessageListener listener);
}
