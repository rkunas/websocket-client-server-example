package eu.kunas.websocketexample.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ramazan on 31.01.18.
 */
@EnableAutoConfiguration
@ComponentScan("eu.kunas.websocketexample.client")
public class Client {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Client.class, args);
    }
}

