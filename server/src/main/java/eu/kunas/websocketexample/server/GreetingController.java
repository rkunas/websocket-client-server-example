package eu.kunas.websocketexample.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ramazan on 05.02.18.
 */
@Controller
@Slf4j
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate broker;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("Received hello: {}" + message.getName());
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(path = "/callclient",method = RequestMethod.GET)
    public void call(){
        broker.convertAndSend("/topic/greetings", new Greeting("Yipeeeeeeaiyeee"));
    }
}
