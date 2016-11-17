package br.com.chico.config;

import lombok.Data;

@Data
public class RabbitMessage<T> {
    public T message;
}
