package br.com.chico.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "amqp.config")
@Component
public class RabbitMQConsumerConfig {

    private Long initialInterval;
    private Long maxInterval;
    private Integer retries;
    private Integer multiplier;
    private Integer consumers;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(consumers);
        factory.setDefaultRequeueRejected(false);
        factory.setAdviceChain(
                org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(retries)
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .backOffOptions(initialInterval, multiplier, maxInterval)
                        .build());

        return factory;
    }



    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(final Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Long getInitialInterval() {
        return initialInterval;
    }

    public void setInitialInterval(final Long initialInterval) {
        this.initialInterval = initialInterval;
    }

    public Long getMaxInterval() {
        return maxInterval;
    }

    public void setMaxInterval(final Long maxInterval) {
        this.maxInterval = maxInterval;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(final Integer retries) {
        this.retries = retries;
    }

    public Integer getConsumers() {
        return consumers;
    }

    public void setConsumers(final Integer consumers) {
        this.consumers = consumers;
    }
}
