package br.com.chico.config.properties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "message.configs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Properties {
    private String queueName;
    private String queueDlq;
    private String routingKey;
    private String exchangeName;
    private String exchangeDlx;
    private String deadLetterRoutingKey;
}