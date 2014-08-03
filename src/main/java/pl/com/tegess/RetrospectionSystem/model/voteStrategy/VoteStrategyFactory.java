package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

/**
 * Created by Szymek.
 */
public interface VoteStrategyFactory {

    public VoteStrategy getVoteStrategy(String className);
}
