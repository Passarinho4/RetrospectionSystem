package pl.com.tegess.RetrospectionSystem.model;

/**
 * Implements VoteStrategy interface and decides
 * could user vote for sticker or not. In this strategy
 * one user has only one vote for the same sticker.
 *
 * @author Szymek
 *
 * @version 1.0
 */
public class OneForOneVoteStrategy implements VoteStrategy {

    /**
     * Returns true if user can vote for sticker or false
     * if user can't vote for sticker. User have only one vote
     * for the same sticker.
     *
     * @param user the user.
     * @param sticker the sticker.
     * @return true if user can vote or false if user can't.
     */
    @Override
    public boolean canVote(User user, Sticker sticker) {
        System.out.println(sticker.containsVoteFrom(user.getToken()));
        return !sticker.containsVoteFrom(user.getToken());
    }
}
