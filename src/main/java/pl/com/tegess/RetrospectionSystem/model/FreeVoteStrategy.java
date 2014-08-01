package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public class FreeVoteStrategy implements VoteStrategy {
    @Override
    public boolean canVote(User user, Sticker sticker) {
        return true;
    }
}
