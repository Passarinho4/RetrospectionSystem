package pl.com.tegess.RetrospectionSystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;

import java.util.List;

/**
 * Created by Szymek.
 */
@Repository
public class RetrospectionRepositoryMongoDB implements RetrospectionRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertRetrospection(Retrospection retrospection) {
        mongoTemplate.insert(retrospection);
    }

    @Override
    public void removeRetrospection(Retrospection retrospection) {
        mongoTemplate.remove(retrospection);
    }

    @Override
    public List<Retrospection> getAllRetrospections() {
        return mongoTemplate.findAll(Retrospection.class);
    }

    @Override
    public List<Retrospection> getRetrospectionByAuthor(String author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(author));
        return mongoTemplate.find(query, Retrospection.class);
    }

    @Override
    public Retrospection getRetrospectionById(String id) {
        return mongoTemplate.findById(id, Retrospection.class);
    }

    @Override
    public Retrospection getRetrospectionByToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("membersTokens").is(token));
        return mongoTemplate.findOne(query, Retrospection.class);
    }

    @Override
    public boolean containsRetrospectionById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Retrospection.class);
    }

    @Override
    public boolean containsRetrospectionByToken(String token) {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        return mongoTemplate.exists(query, Retrospection.class);
    }

    @Override
    public void modifyRetrospection(Retrospection retrospection) {
        mongoTemplate.save(retrospection);
    }

}
