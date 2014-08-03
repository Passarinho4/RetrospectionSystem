package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.stickers.StickerLeaf;
import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.FreeVoteStrategy;

public class FreeVoteStrategyTest {

    @Test
    public void testCanVote(){

        //given
        FreeVoteStrategy underTest = new FreeVoteStrategy();
        User user = new Member("token");
        Sticker sticker = new StickerLeaf(1, "content", "author");
        //when

        //then
        Assert.assertEquals(true, underTest.canVote(user, sticker));
    }

    @Test
    public void testGetDescription() {

        //given
        FreeVoteStrategy underTest = new FreeVoteStrategy();

        //then
        Assert.assertEquals(String.class, underTest.getDescription().getClass());

    }
}