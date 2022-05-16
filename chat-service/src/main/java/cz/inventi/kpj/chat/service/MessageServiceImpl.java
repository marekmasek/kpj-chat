package cz.inventi.kpj.chat.service;

import cz.inventi.kpj.chat.mapper.MessageMapper;
import cz.inventi.kpj.chat.messaging.MessageBroker;
import cz.inventi.kpj.chat.messaging.MessageListener;
import cz.inventi.kpj.chat.model.MessageEvent;
import cz.inventi.kpj.openapi.model.Message;
import cz.inventi.kpj.openapi.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService, MessageListener {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageBroker messageBroker;

    @PostConstruct
    private void init() {
        messageBroker.registerListener(this);
    }

    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    @Override
    public UUID createMessage(MessageRequest messageRequest) {
        var messageEvent = messageMapper.requestToEvent(messageRequest);
        messageEvent.setId(UUID.randomUUID());
        messageEvent.setCreated(OffsetDateTime.now());
        messageBroker.publish(messageEvent);
        return messageEvent.getId();
    }

    @Override
    public List<Message> listMessages() {
        return messages;
    }

    @Override
    public void onMessage(MessageEvent messageEvent) {
        messages.add(messageMapper.eventToDTO(messageEvent));
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMessage() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setName("Masek");
        messageRequest.setMessage("TODO: your message :-)");
        createMessage(messageRequest);
    }
}
