package pl.com.tegess.RetrospectionSystem.repositories;

/**
 * Created by Szymek.
 */
public interface CounterRepository {

    public int getNextSequence(String name);
}
