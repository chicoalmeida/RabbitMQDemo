package br.com.chico.config.queues;

import br.com.chico.config.properties.RabbitMQProperties;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeclareTopicQueue {

    @Autowired
    private RabbitMQProperties properties;

    @Autowired
    private ConnectionFactory connectionFactory;


    @Bean
    public Void teste() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        properties.getConfigs().forEach(configuration -> {

            TopicExchange topicExchange = new TopicExchange(configuration.getExchangeName(), true, false, null);
            rabbitAdmin.declareExchange(topicExchange);

            TopicExchange topicExchangeDlx = new TopicExchange(configuration.getExchangeDlx(), true, false, null);
            rabbitAdmin.declareExchange(topicExchangeDlx);

            Queue queue = new Queue(configuration.getQueueName(), true, false, false);
            Queue queueDlq = new Queue(configuration.getExchangeDlx(), true, false, false);

            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareQueue(queueDlq);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(configuration.getRoutingKey()));
            rabbitAdmin.declareBinding(BindingBuilder.bind(queueDlq).to(topicExchangeDlx).with(configuration.getDeadLetterRoutingKey()));

        });

        return null;


    }
}
