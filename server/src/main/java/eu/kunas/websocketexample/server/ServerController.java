package eu.kunas.websocketexample.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ramazan on 05.02.18.
 */
@Controller
@Slf4j
public class ServerController {

    @Autowired
    private SimpMessagingTemplate broker;

    @MessageMapping("/hello")
    @SendTo("/queue/greetings")
    public List<BusinessObject> greeting(List<BusinessObject> message) throws Exception {
        log.debug("Received BusinsessObjects: " + message.size());
        return Collections.emptyList();
    }

    @RequestMapping(path = "/callclient",method = RequestMethod.GET)
    public void call(){

        List<BusinessObject> list = new ArrayList<>();

        for(int i=0; i<10;i++){
            BusinessObject businessObject = new BusinessObject();
            businessObject.setName( i + " Business");
            businessObject.setBid(i);
            list.add(businessObject);
        }
        broker.convertAndSend("/queue/greetings", list);
    }
}
