package br.com.chico.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class TestListener {

    @RabbitListener(queues = "fila1")
    public void read(final Message message) throws UnsupportedEncodingException {

    }
}
