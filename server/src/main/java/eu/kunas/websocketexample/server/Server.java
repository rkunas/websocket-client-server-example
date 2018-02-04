package eu.kunas.websocketexample.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ramazan on 31.01.18.
 */
@SpringBootApplication
@Slf4j
public class Server {

    @Autowired
    private SimpMessagingTemplate broker;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        log.info("Received hello: {}", message.getName());
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(path = "/callclient",method = RequestMethod.GET)
    public void call(){
        broker.convertAndSend("/topic/greetings", new Greeting("Yipeeeeeeaiyeee"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
