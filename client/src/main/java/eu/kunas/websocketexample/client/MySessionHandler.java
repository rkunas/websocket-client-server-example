package eu.kunas.websocketexample.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramazan on 31.01.18.
 */

@Component
@Slf4j
public class MySessionHandler extends StompSessionHandlerAdapter {

    private StompSession session;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        this.session = session;
        session.subscribe("/queue/greetings", this);

    }

    public void send(String message) {
        synchronized(session) {

            List<BusinessObject> list = new ArrayList<>();

            for(int i=0; i<10;i++){
                BusinessObject businessObject = new BusinessObject();
                businessObject.setName( i + " Business");
                businessObject.setBid(i);
                list.add(businessObject);
            }

            session.send("/app/businessobjects", list);
        }
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return List.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.debug("Received Business Objects:  " + ((List<BusinessObject>) payload).size());
    }
}
