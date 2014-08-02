package pl.com.tegess.RetrospectionSystem.model;

/**
 * Implements VoteStrategy interface and decides
 * could user vote for sticker or not. In this strategy
 * every user has unlimited number of votes.
 *
 * @author Szymek
 *
 * @version 1.0
 */
public class FreeVoteStrategy implements VoteStrategy {

    /**
     * Returns true every time.
     *
     * @param user the user.
     * @param sticker the sticker.
     * @return true
     */
    @Override
    public boolean canVote(User user, Sticker sticker) {
        return true;
    }
}
