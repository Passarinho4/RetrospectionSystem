package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void testGettersAndSetters(){

        //given
        Member underTest = new Member("token");

        //getters test
        Assert.assertEquals("token", underTest.getToken());
        Assert.assertEquals(0, underTest.getVotesNumber());
        Assert.assertEquals(0, underTest.getNumberOfVotesForSticker(2));

    }

    @Test
    public void testAddAndRemoveStickerToVoted() {

        //given
        Member underTest = new Member("token");

        //when
        underTest.addStickerToVoted(1);
        underTest.addStickerToVoted(4);

        //then
        Assert.assertEquals(2, underTest.getVotesNumber());

        //when
        underTest.removeStickerFromVoted(4);

        //then
        Assert.assertEquals(1, underTest.getVotesNumber());

    }

    @Test
    public void testGetNumberOfVotesForSticker() {

        //given
        Member underTest = new Member("token");

        //when
        underTest.addStickerToVoted(1);
        underTest.addStickerToVoted(1);
        underTest.addStickerToVoted(1);
        underTest.addStickerToVoted(5);
        underTest.addStickerToVoted(5);

        //then
        Assert.assertEquals(3, underTest.getNumberOfVotesForSticker(1));
        Assert.assertEquals(2, underTest.getNumberOfVotesForSticker(5));

    }
}