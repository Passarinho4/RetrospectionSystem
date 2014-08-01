package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public interface VoteStrategy {

    public boolean canVote(User user, Sticker sticker);

}
