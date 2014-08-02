package pl.com.tegess.RetrospectionSystem.model;

/**
 * Represent vote strategy (Strategy pattern).
 */
public interface VoteStrategy {

    /**
     * Returns true if user can vote for sticker.
     * Returns false if user can't vote for sticker.
     * @see pl.com.tegess.RetrospectionSystem.model.User
     * @see pl.com.tegess.RetrospectionSystem.model.Sticker
     * @see pl.com.tegess.RetrospectionSystem.model.OneForOneVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.LimitedVotesPerMemberVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.FreeVoteStrategy
     *
     * @param user the user.
     * @param sticker the sticker.
     * @return true or false.
     */
    public boolean canVote(User user, Sticker sticker);

}
