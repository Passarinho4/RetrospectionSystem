package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public class OneForOneVoteStrategy implements VoteStrategy {


    @Override
    public boolean canVote(User user, Sticker sticker) {
        System.out.println(sticker.containsVoteFrom(user.getToken()));
        return !sticker.containsVoteFrom(user.getToken());
    }
}
