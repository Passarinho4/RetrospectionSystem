package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

/**
 * Default implementation od VoteStrategyFactory.
 * Provides creation of new VoteStrategy objects.
 *
 * @author Szymek
 * @version 1.0
 */
public class DefaultVoteStrategyFactory implements VoteStrategyFactory {

    /**
     * Returns VoteStrategy object. Created from param class name,
     * works fine only if this class is located in
     * pl.com.tegess.RetrospectionSystem.model.voteStrategy package. If class
     * is located in different package should use other implementation of
     * VoteStrategyFactory.
     *
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategyFactory
     *
     * @param className the class.
     * @return the VoteStrategy object.
     */
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
