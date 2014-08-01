package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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

}