package pl.com.tegess.RetrospectionSystem.model;

import org.apache.commons.lang3.RandomStringUtils;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of Generator interfaces which
 * generates unique ids and tokens for concrete Retrospection
 * Repository. Generates random ids (10 chars) and tokens (12 chars),
 * using RandomStringUtils.
 *
 */
public class DefaultGenerator implements Generator {

    /**
     * The Retrospection Repository.
     */
    private RetrospectionRepository retrospectionRepository;

    /**
     * Sets Retrospection Repository.
     * @param retrospectionRepository the Retrospection Repository.
     */
    public DefaultGenerator(RetrospectionRepository retrospectionRepository) {
        this.retrospectionRepository = retrospectionRepository;
    }

    /**
     * Returns unique id (10 chars). Use Retrospection Repository to be sure,
     * that id is unique.
     *
     * @return String id.
     */
    @Override
    public String getId() {
        RandomStringUtils randomStringFactory =  new RandomStringUtils();
        String id = randomStringFactory.random(10, true, true);
        while (this.retrospectionRepository.containsRetrospectionById(id)){
            id = randomStringFactory.random(10, true, true);
        }
        return id;
    }

    /**
     * Returns List of unique tokens (12 chars every). Use Retrospection Repository
     * to be sure, that id is unique.
     *
     * @param amount the number of tokens.
     * @return the List of tokens.
     */
    @Override
    public List<String> getTokens(Integer amount) {
        RandomStringUtils randomStringFactory = new RandomStringUtils();
        List<String> tokens = new ArrayList<String>();
        for(int i=0; i<amount; i++) {
            String token = randomStringFactory.random(12, true, true);
            while (this.retrospectionRepository.containsRetrospectionByToken(token)) {
                token = randomStringFactory.random(12, true, true);
            }
            tokens.add(token);
        }
        return tokens;
    }

}
