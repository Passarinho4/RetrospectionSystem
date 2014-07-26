package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Szymek.
 */
@Document
public class Counter {
    @Id
    private String id;
    private int sequence;

    public Counter(String id) {
        this.id = id;
        this.sequence = 0;
    }

    public int getSequence() {
        return sequence;
    }

    public String getId() {
        return id;
    }
}
