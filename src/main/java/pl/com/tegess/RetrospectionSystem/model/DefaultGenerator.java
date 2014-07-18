package pl.com.tegess.RetrospectionSystem.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymek.
 */
public class DefaultGenerator implements Generator {

    private RetrospectionRepository retrospectionRepository;

    public DefaultGenerator(RetrospectionRepository retrospectionRepository) {
        this.retrospectionRepository = retrospectionRepository;
    }


    @Override
    public String getId() {
        RandomStringUtils randomStringFactory =  new RandomStringUtils();
        String id = randomStringFactory.random(10, true, true);
        while (this.retrospectionRepository.containsRetrospectionById(id)){
            id = randomStringFactory.random(10, true, true);
        }
        return id;
    }

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
