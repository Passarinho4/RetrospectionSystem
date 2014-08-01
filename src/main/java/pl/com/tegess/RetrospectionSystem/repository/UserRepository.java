package pl.com.tegess.RetrospectionSystem.repository;

import pl.com.tegess.RetrospectionSystem.model.User;

import java.util.List;

/**
 * Created by Szymek.
 */
public interface UserRepository {

    public void insertUser(User user);
    public void removeUser(User user);
    public User getUserByToken(String token);
    public void modifyUser(User user);
    public void removeAllUsers(List<String> tokens);
}
