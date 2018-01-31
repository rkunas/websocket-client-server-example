package eu.kunas.websocketexample.server;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ramazan on 31.01.18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
    private String content;
}
