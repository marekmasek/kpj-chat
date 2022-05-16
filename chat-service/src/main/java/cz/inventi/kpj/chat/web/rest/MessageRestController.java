package cz.inventi.kpj.chat.web.rest;

import cz.inventi.kpj.chat.service.MessageService;
import cz.inventi.kpj.openapi.api.MessagesApi;
import cz.inventi.kpj.openapi.model.Message;
import cz.inventi.kpj.openapi.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageRestController implements MessagesApi {

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<Void> createMessage(MessageRequest messageRequest) {
        messageService.createMessage(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Message>> listMessages() {
        return new ResponseEntity<>(messageService.listMessages(), HttpStatus.OK);
    }
}
