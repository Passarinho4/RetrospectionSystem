package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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
}