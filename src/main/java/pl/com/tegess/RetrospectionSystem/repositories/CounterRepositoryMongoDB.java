package pl.com.tegess.RetrospectionSystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.Counter;

/**
 * Created by Szymek.
 */
@Repository
public class CounterRepositoryMongoDB implements CounterRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public int getNextSequence(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(name));
        Counter counter = mongoTemplate.findAndModify(query, new Update().inc("sequence", 1),
                FindAndModifyOptions.options().returnNew(true), Counter.class);
        if(counter==null){
            counter = new Counter(name);
            mongoTemplate.insert(counter);
        }
        return counter.getSequence();
    }
}
