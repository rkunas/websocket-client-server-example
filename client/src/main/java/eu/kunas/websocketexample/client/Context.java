package eu.kunas.websocketexample.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ramazan on 05.02.18.
 */

@Configuration
public class Context {

    @Bean
    MySessionHandler mySessionHandler(){
        return new MySessionHandler();
    }
}
