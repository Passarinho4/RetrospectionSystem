package pl.com.tegess.RetrospectionSystem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;

import java.util.ArrayList;
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
    public List<User> getUsersByTokens(List<String> tokens) {

        List<User> users = new ArrayList<User>();
        for(String token: tokens){
            users.add(getUserByToken(token));
        }
        return users;
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
