package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents counter, which gives auto increment sequence
 * every time when call getSequence method. Contains id,
 * so can be used to generates many auto increment sequences.
 *
 * @author Szymek
 *
 * @version 1.0
 */
@Document
public class Counter {

    @Id
    /**
     * The counter id.
     */
    private String id;

    /**
     * The counter actual sequence.
     */
    private int sequence;

    /**
     * Constructor sets id value and sets sequence to 0.
     * So first returned int will be 1.
     * @param id the counter id.
     */
    public Counter(String id) {
        this.id = id;
        this.sequence = 0;
    }

    /**
     * Returns next auto increment value.
     *
     * @return next value.
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Simple getter for counter id.
     *
     * @return counter id.
     */
    public String getId() {
        return id;
    }
}
