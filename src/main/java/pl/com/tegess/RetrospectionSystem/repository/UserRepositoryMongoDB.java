package pl.com.tegess.RetrospectionSystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.Member;
import pl.com.tegess.RetrospectionSystem.model.User;

import java.util.List;

/**
 * Created by Szymek.
 */
@Repository
public class UserRepositoryMongoDB implements UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertUser(User user) {
        this.mongoTemplate.insert(user);
    }

    @Override
    public void removeUser(User user) {
        this.mongoTemplate.remove(user);
    }

    @Override
    public User getUserByToken(String token) {
        return this.mongoTemplate.findById(token, Member.class);
    }

    @Override
    public void modifyUser(User user) {
        this.mongoTemplate.save(user);
    }

    @Override
    public void removeAllUsers(List<String> tokens) {
        for(String t: tokens){
            removeUser(getUserByToken(t));
        }
    }
}
