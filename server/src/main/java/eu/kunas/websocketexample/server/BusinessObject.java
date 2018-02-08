package eu.kunas.websocketexample.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ramazan on 08.02.18.
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessObject {
    private int bid;
    private String name;
}
