package pl.com.tegess.RetrospectionSystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.Person;

import java.util.List;

/**
 * Created by Szymek.
 */
@Repository
public class PersonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void insert(Person person){
        mongoTemplate.insert(person);
    }

    public int getPersonNumber(){
        return mongoTemplate.findAll(Person.class).size();
    }

    public void deleteAll(){
        mongoTemplate.remove(new Query(), Person.class);
    }

    public String toString(){
        List<Person> list = mongoTemplate.findAll(Person.class);
        String result;
        result="";
        for(Person p: list){
            result = result+ " " + p.getName();
        }
        return result;
    }
}
