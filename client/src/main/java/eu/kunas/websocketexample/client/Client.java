package eu.kunas.websocketexample.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/**
 * Created by ramazan on 31.01.18.
 */
@Controller
@EnableAutoConfiguration
public class Client {

    @Autowired
    private MySessionHandler mySessionHandler;

    @RequestMapping("/connect")
    @ResponseBody
    public String call() {

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        String url = "ws://127.0.0.1:8892/hello";
        stompClient.connect(url, mySessionHandler);

        return "OK";
    }

    @RequestMapping(path = "/callserver", method = RequestMethod.GET)
    public String callServer(){

        mySessionHandler.send("Client is calling");
        return "OK";
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Client.class, args);
    }
}

