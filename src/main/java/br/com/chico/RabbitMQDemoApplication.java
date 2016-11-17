package br.com.chico;

import br.com.chico.config.properties.RabbitMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQDemoApplication implements CommandLineRunner {


    @Autowired
    RabbitMQProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQDemoApplication.class, args);

    }

    @Override
    public void run(final String... strings) throws Exception {
        System.out.println(properties);

    }
}
