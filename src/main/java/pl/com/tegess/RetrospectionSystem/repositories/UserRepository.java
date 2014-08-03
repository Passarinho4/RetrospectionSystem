package pl.com.tegess.RetrospectionSystem.repositories;

import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;

import java.util.List;

/**
 * Created by Szymek.
 */
public interface UserRepository {

    public void insertUser(User user);
    public void removeUser(User user);
    public User getUserByToken(String token);
    public List<User> getUsersByTokens(List<String> tokens);
    public List<Member> getAllUsers();
    public void modifyUser(User user);
    public void removeAllUsers(List<String> tokens);
}
