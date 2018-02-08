package eu.kunas.websocketexample.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ramazan on 31.01.18.
 */
@SpringBootApplication
@Slf4j
@ComponentScan("eu.kunas.websocketexample.server")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
