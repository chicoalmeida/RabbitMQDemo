package br.com.chico.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class MessageContext {

    private RabbitMessage rabbitMessage;
    private RabbitMQMapping rabbitMQMapping;

    public static MessageContext createMessageContext(final RabbitMessage rabbitMessage, final RabbitMQMapping rabbitMQMapping) {
        return MessageContext
                .builder()
                .rabbitMessage(rabbitMessage)
                .rabbitMQMapping(rabbitMQMapping)
                .build();
    }
}
