package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.stickers.StickerLeaf;
import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.OneForOneVoteStrategy;

public class OneForOneVoteStrategyTest {

    @Test
    public void testCanVote() {

        //given
        OneForOneVoteStrategy underTest = new OneForOneVoteStrategy();
        User user = new Member("token");
        Sticker sticker = new StickerLeaf(1, "content", "author");

        //first test (empty)
        Assert.assertEquals(true, underTest.canVote(user, sticker));

        //when
        user.addStickerToVoted(1);
        sticker.addVote("token");

        //then
        Assert.assertEquals(false, underTest.canVote(user, sticker));

    }
    @Test
    public void testGetDescription() {

        //given
        OneForOneVoteStrategy underTest = new OneForOneVoteStrategy();

        //then
        Assert.assertEquals(String.class, underTest.getDescription().getClass());

    }
}