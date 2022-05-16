package cz.inventi.kpj.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MessageEvent {
    private UUID id;
    private String name;
    private String message;
    private OffsetDateTime created;
}
