package cz.inventi.kpj.chat.service;

import cz.inventi.kpj.openapi.model.Message;
import cz.inventi.kpj.openapi.model.MessageRequest;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    UUID createMessage(MessageRequest messageRequest);

    List<Message> listMessages();
}
