package pl.com.tegess.RetrospectionSystem.repository;

import org.springframework.stereotype.Repository;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;

import java.util.List;

/**
 * Created by Szymek.
 */

public interface RetrospectionRepository {

    public void insertRetrospection(Retrospection retrospection);
    public void removeRetrospection(Retrospection retrospection);
    public List<Retrospection> getAllRetrospections();
    public List<Retrospection> getRetrospectionByAuthor(String author);
    public Retrospection getRetrospectionById(String id);
    public Retrospection getRetrospectionByToken(String token);
    public boolean containsRetrospectionById(String id);
    public boolean containsRetrospectionByToken(String token);
    public void modifyRetrospection(Retrospection retrospection);
}
