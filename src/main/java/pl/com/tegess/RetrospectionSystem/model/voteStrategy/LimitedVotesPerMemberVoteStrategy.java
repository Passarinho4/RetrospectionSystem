package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.users.User;

/**
 * Implements VoteStrategy interface and decides
 * could user vote for sticker or not. In this strategy
 * one user has limited votes. Default value of limit is 10.
 *
 * @author Szymek
 *
 * @version 1.0
 */
public class LimitedVotesPerMemberVoteStrategy implements VoteStrategy {

    /**
     * The limit of votes per user.
     */
    private final int maxVotesPerMember;

    /**
     * Default constructor, sets votes limit to 10.
     */
    public LimitedVotesPerMemberVoteStrategy() {
        this.maxVotesPerMember = 10;
    }

    /**
     * Constructor sets votes limit.
     * @param maxVotesPerMember votes limit.
     */
    public LimitedVotesPerMemberVoteStrategy(int maxVotesPerMember) {
        this.maxVotesPerMember = maxVotesPerMember;
    }

    /**
     * Returns true if user can vote for sticker or false
     * if user can't vote. User has limited number of votes.
     * If user vote less times then limit returns true. In another
     * way returns false.
     *
     * @param user the user.
     * @param sticker the sticker.
     * @return true if user can vote, false if user can't.
     */
    @Override
    public boolean canVote(User user, Sticker sticker) {
        return user.getVotesNumber()<this.maxVotesPerMember;
    }

    /**
     * Returns description of vote strategy. The description should
     * contains information about number of votes per user or some
     * other votes limits.
     *
     * @return the strategy description.
     */
    @Override
    public String getDescription() {
        String description = "Limited votes per member, means that every user has only ";
        return description + this.maxVotesPerMember + " votes";
    }
}
