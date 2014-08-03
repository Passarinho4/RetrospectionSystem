package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

/**
 * Created by Szymek.
 */
public class DefaultVoteStrategyFactory implements VoteStrategyFactory {
    @Override
    public VoteStrategy getVoteStrategy(String className) {
        Class c = null;
        try {
            c = Class.forName("pl.com.tegess.RetrospectionSystem.model.voteStrategy."+ className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (VoteStrategy)o;
    }
}
