package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Type;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.users.User;

/**
 * Represent vote strategy (Strategy pattern).
 */
public interface VoteStrategy {

    /**
     * Returns true if user can vote for sticker.
     * Returns false if user can't vote for sticker.
     * @see pl.com.tegess.RetrospectionSystem.model.users.User
     * @see pl.com.tegess.RetrospectionSystem.model.stickers.Sticker
     * @see OneForOneVoteStrategy
     * @see LimitedVotesPerMemberVoteStrategy
     * @see FreeVoteStrategy
     *
     * @param user the user.
     * @param sticker the sticker.
     * @return true or false.
     */
    public boolean canVote(User user, Sticker sticker);

    /**
     * Returns description of vote strategy. The description should
     * contains information about number of votes per user or some
     * other votes limits.
     *
     * @return the strategy description.
     */
    public String getDescription();

    /**
     * Returns max value of votes per one sticker.
     *
     * @param retrospection the retrospection.
     * @param type the sticker.
     * @return the max value.
     */
    public int getMaxVotesValue(Retrospection retrospection, Type type);
}
