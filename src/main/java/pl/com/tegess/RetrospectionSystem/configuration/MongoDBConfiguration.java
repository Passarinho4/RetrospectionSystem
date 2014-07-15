package pl.com.tegess.RetrospectionSystem.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Szymek.
 */

@Configuration
@EnableMongoRepositories("pl.com.tegess.RetrospectionSystem.repository")
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "RetrospectionSystemDB";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }
}
