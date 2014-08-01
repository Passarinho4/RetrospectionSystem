package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

public class StickerLeafTest {

    @Test
    public void testGettersOfStickerLeaf(){

        //given
        String content = "longlongcontentlongcontent";
        String shortContent = content.substring(0, 20) + "...";
        StickerLeaf underTest = new StickerLeaf(1, content, "author");

        //then
        Assert.assertEquals(new Integer(1), underTest.getId());
        Assert.assertEquals(content, underTest.getContent());
        Assert.assertEquals("author", underTest.getAuthor());
        Assert.assertEquals(shortContent, underTest.getShortContent());

        //set content
        underTest.setContent("new content");

        //then
        Assert.assertEquals("new content", underTest.getContent());

    }

    @Test
    public void testEmptyVotes(){

        //given
        StickerLeaf underTest = new StickerLeaf(1, "content", "author");

        //then

        //given
        Assert.assertEquals(new Integer(0), underTest.getVotes());
    }

    @Test
    public void testFilledVotes(){

        //given
        StickerLeaf underTest = new StickerLeaf(1, "content", "author");

        //when
        underTest.setVotes(5);

        //then
        Assert.assertEquals(new Integer(5), underTest.getVotes());
    }

    @Test
    public void testAddRemoveVotesAndCanVote(){

        //given
        StickerLeaf underTest = new StickerLeaf(1, "content", "author");

        //when
        underTest.addVote("token");

        //then test addVote and containsVoteFrom
        Assert.assertEquals(true, underTest.containsVoteFrom("token"));
        Assert.assertEquals(false, underTest.containsVoteFrom("someOtherToken"));
        Assert.assertEquals(new Integer(1), underTest.getVotes());

        //when
        underTest.removeVote("token");

        //then test removeVote
        Assert.assertEquals(false, underTest.containsVoteFrom("token"));
        Assert.assertEquals(new Integer(0), underTest.getVotes());
    }

    @Test
    public void testIsComposite(){

        //given
        StickerLeaf underTest = new StickerLeaf(1, "content", "author");

        //then
        Assert.assertEquals(false, underTest.isComposite());
    }
}