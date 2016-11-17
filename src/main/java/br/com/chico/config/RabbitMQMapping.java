package br.com.chico.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RabbitMQMapping {
    private String queueName;
    private String queueDlq;
    private String exchangeName;
    private String exchangeDlx;
    private String routingKeyDlq;
    private String routingKeyDlx;
}
