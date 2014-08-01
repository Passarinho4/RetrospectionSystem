package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public class LimitedVotesPerMemberVoteStrategy implements VoteStrategy {

    private final int maxVotesPerMember;

    public LimitedVotesPerMemberVoteStrategy() {
        this.maxVotesPerMember = 10;
    }

    public LimitedVotesPerMemberVoteStrategy(int maxVotesPerMember) {
        this.maxVotesPerMember = maxVotesPerMember;
    }

    @Override
    public boolean canVote(User user, Sticker sticker) {
        return user.getVotesNumber()<this.maxVotesPerMember;
    }
}
