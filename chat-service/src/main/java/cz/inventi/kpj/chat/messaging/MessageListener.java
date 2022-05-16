package cz.inventi.kpj.chat.messaging;

import cz.inventi.kpj.chat.model.MessageEvent;

public interface MessageListener {
    void onMessage(MessageEvent messageEvent);
}
