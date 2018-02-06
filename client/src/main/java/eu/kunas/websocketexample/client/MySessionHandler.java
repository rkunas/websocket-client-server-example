package eu.kunas.websocketexample.client;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * Created by ramazan on 31.01.18.
 */

@Component
public class MySessionHandler extends StompSessionHandlerAdapter {

    private StompSession session;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        this.session = session;
        session.subscribe("/topic/greetings", this);

    }

    public void send(String message) {
        synchronized(session) {
            HelloMessage helloMessage = new HelloMessage();
            helloMessage.setName("Client");
            session.send("/app/hello", helloMessage);
        }
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received: {} " + ((Greeting) payload).getContent());
    }
}
