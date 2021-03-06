package pl.com.tegess.RetrospectionSystem.model;


import org.junit.Assert;
import org.junit.Test;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.stickers.StickerComposite;
import pl.com.tegess.RetrospectionSystem.model.stickers.StickerLeaf;

import java.util.ArrayList;
import java.util.List;

public class StickerCompositeTest {

    @Test
    public void testGettersAndSettersOfStickerComposite(){

        //given
        String content = "testLALAcontentlongAndtestonemoretime";
        String shortContent = content.substring(0, 20) + "...";
        StickerComposite underTest = new StickerComposite(1, content, "author");

        //test getters
        Assert.assertEquals(new Integer(1), underTest.getId());
        Assert.assertEquals(content, underTest.getContent());
        Assert.assertEquals(shortContent, underTest.getShortContent());
        Assert.assertEquals("author", underTest.getAuthor());

        //set content
        underTest.setContent("new content");

        //then
        Assert.assertEquals("new content", underTest.getContent());

    }

    @Test
    public  void testAddRemoveAndGetChild(){

        //given
        Sticker child = new StickerLeaf(1, "child", "author");
        List<Sticker> children = new ArrayList<Sticker>();
        children.add(child);
        StickerComposite underTest = new StickerComposite(2, "content", "author");

        //test without children
        Assert.assertEquals(null, underTest.getChild(1));

        //when
        underTest.addChild(child);

        //test with one child
        Assert.assertEquals(child, underTest.getChild(1));
        Assert.assertEquals(children, underTest.getChildren());

        //when
        underTest.removeChild(underTest.getChild(1));

        //test without children
        Assert.assertEquals(null, underTest.getChild(1));
        Assert.assertEquals(new ArrayList<Sticker>(), underTest.getChildren());

    }

    @Test
    public void testAddRemoveVotesAndCanVote(){

        //given
        StickerComposite underTest = new StickerComposite(1, "content", "author");

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
        StickerComposite underTest = new StickerComposite(1, "content", "author");

        //when

        //then
        Assert.assertEquals(true, underTest.isComposite());
    }

}