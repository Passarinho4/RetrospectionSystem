package pl.com.tegess.RetrospectionSystem.repository;

/**
 * Created by Szymek.
 */
public interface CounterRepository {

    public int getNextSequence(String name);
}
