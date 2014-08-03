package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

/**
 * The factory of vote strategies. Has only one method,
 * which creates new VoteStrategy object.
 *
 * @author Szymek
 *
 * @version 1.0
 */
public interface VoteStrategyFactory {

    /**
     * Returns new VoteStrategy object from param class.
     *
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.OneForOneVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.FreeVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.LimitedVotesPerMemberVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.DefaultVoteStrategyFactory
     *
     * @param className the class.
     * @return new VoteStrategy object.
     */
    public VoteStrategy getVoteStrategy(String className);
}
