package pl.com.tegess.RetrospectionSystem.model;

import org.apache.commons.lang3.RandomStringUtils;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepositoryMongoDB;

/**
 * Created by Szymek.
 */
public class DefaultIdGenerator implements IdGenerator {


    @Override
    public String getId() {
        RandomStringUtils randomStringFactory =  new RandomStringUtils();
        String id = randomStringFactory.random(10, true, true);
        //FAAIIILLLRetrospectionRepository repository = new RetrospectionRepositoryMongoDB();
        //while (repository.containsRetrospectionById(id)){
        //    id = randomStringFactory.random(10, true, true);
        //}
        return id;
    }
}
