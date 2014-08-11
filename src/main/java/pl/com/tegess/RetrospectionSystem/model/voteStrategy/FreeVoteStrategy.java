package pl.com.tegess.RetrospectionSystem.model.voteStrategy;

import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Type;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.users.User;

import java.util.List;

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

    /**
     * Returns description of vote strategy. The description should
     * contains information about number of votes per user or some
     * other votes limits.
     *
     * @return the strategy description.
     */
    @Override
    public String getDescription() {
        return "Free vote strategy means that every user has unlimited number of votes.";
    }

    /**
     * Returns max value of votes per one sticker. In this strategy returns number of votes per one category.
     * For example for all mad stickers.
     *
     * @param retrospection the retrospection.
     * @param type the sticker's type.
     * @return the max value.
     */
    @Override
    public int getMaxVotesValue(Retrospection retrospection, Type type) {
        int result=0;
        List<Sticker> list = retrospection.getStickersList(type, null);
        for(Sticker tmp : list){
            result = result + tmp.getVotes();
        }
        return result;
    }
}
