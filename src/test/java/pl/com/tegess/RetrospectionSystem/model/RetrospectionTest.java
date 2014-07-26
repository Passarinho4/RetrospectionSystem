package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RetrospectionTest {

    @Test
    public void testGettersAndSettersOfRetrospection(){

        //given
        Retrospection underTest = new Retrospection("id", "author", "question", new ArrayList<String>());

        //test getters
        Assert.assertEquals("id", underTest.getRetrospectionId());
        Assert.assertEquals("author", underTest.getAuthor());
        Assert.assertEquals("question", underTest.getQuestion());
        Assert.assertEquals(true, underTest.getStatus());

        //when
        underTest.setStatus(false);

        //then
        Assert.assertEquals(false, underTest.getStatus());
    }

    @Test
    public void testGetMembersNumberEmptyRetrospection(){

        //given
        List<String> tokens = new ArrayList<String>();
        final Integer tokensNumber = tokens.size();
        Retrospection underTest = new Retrospection("id", "author", "question", tokens);

        //when
        Integer underTestTokensNumber = underTest.getMembersNumber();

        //then
        Assert.assertEquals(tokensNumber, underTestTokensNumber);

    }

    @Test
    public void testGetMembersNumberFilledRetrospection(){

        //given
        List<String> tokens = new ArrayList<String>();
        tokens.add("token1");
        tokens.add("token2");
        tokens.add("token3");
        final Integer tokensNumber = tokens.size();
        Retrospection underTest = new Retrospection("id", "author", "question", tokens);

        //when
        Integer underTestTokensNumber = underTest.getMembersNumber();

        //then
        Assert.assertEquals(tokensNumber, underTestTokensNumber);
    }

    @Test
    public void testContainsTokenFilledRetrospection() {

        //given
        List<String> tokens = new ArrayList<String>();
        tokens.add("token1");
        tokens.add("token2");
        tokens.add("token3");
        Retrospection underTest = new Retrospection("id", "author", "question", tokens);
        final boolean containsToken2 = true;
        final boolean containsToken4 = false;

        //when
        boolean underTestContainsToken2 = underTest.containsToken("token2");
        boolean underTestContainsToken4 = underTest.containsToken("token4");

        //then
        Assert.assertEquals(containsToken2, underTestContainsToken2);
        Assert.assertEquals(containsToken4, underTestContainsToken4);
    }

    @Test
    public void testAddGetStickerToRetrospection() {

        //given
        Retrospection underTest = new Retrospection("id", "author", "question", new ArrayList<>());
        Sticker sticker = new StickerLeaf(1, "leafContent", "author");

        //when
        underTest.addSticker(sticker, Type.MAD);
        Sticker underTestContainsSticker = underTest.getStickerById(Type.MAD, 1);

        //then
        Assert.assertEquals(sticker, underTestContainsSticker);

    }

    @Test
    public void testRemoveStickerFromRetrospection(){

        //given
        Retrospection underTest = new Retrospection("id", "author", "question", new ArrayList<>());
        Sticker sticker = new StickerLeaf(1, "leafContent", "author");

        //when
        underTest.addSticker(sticker, Type.MAD);
        Assert.assertEquals(sticker, underTest.getStickerById(Type.MAD, 1));
        underTest.removeSticker(sticker, Type.MAD);

        //then
        Assert.assertEquals(null, underTest.getStickerById(Type.MAD, 1));
    }

    @Test
    public void testGetStickersListFromRetrospection() {

        //given
        List<String> tokens = new ArrayList<String>();
        tokens.add("token");
        Retrospection underTest = new Retrospection("id", "author", "question", tokens);
        Sticker madSticker = new StickerLeaf(1, "madLeafContent", "leafAuthor");
        Sticker gladSticker = new StickerLeaf(2, "gladLeafContent", "token");
        Sticker newIdeaSticker = new StickerLeaf(3, "newIdeaLeafContent", "leafAuthor");
        underTest.addSticker(madSticker, Type.MAD);
        underTest.addSticker(gladSticker, Type.GLAD);
        underTest.addSticker(newIdeaSticker, Type.NEWIDEA);

        List<Sticker> madStickerList = new ArrayList<Sticker>();
        madStickerList.add(madSticker);
        List<Sticker> gladStickerList = new ArrayList<Sticker>();
        gladStickerList.add(gladSticker);
        List<Sticker> newIdeaStickerList = new ArrayList<Sticker>();
        newIdeaStickerList.add(newIdeaSticker);

        //when
        List<Sticker> underTestMadStickerList = underTest.getStickersList(Type.MAD, null);
        List<Sticker> underTestGladStickerList = underTest.getStickersList(Type.GLAD, null);
        List<Sticker> underTestNewIdeaStickerList = underTest.getStickersList(Type.NEWIDEA, null);

        List<Sticker> underTestMadStickerListToken = underTest.getStickersList(Type.MAD, "token");
        List<Sticker> underTestGladStickerListToken = underTest.getStickersList(Type.GLAD, "token");

        //then
        Assert.assertEquals(madStickerList, underTestMadStickerList);
        Assert.assertEquals(gladStickerList, underTestGladStickerList);
        Assert.assertEquals(newIdeaStickerList, underTestNewIdeaStickerList);

        Assert.assertEquals(new ArrayList<Sticker>(), underTestMadStickerListToken);
        Assert.assertEquals(gladStickerList, underTestGladStickerListToken);
    }

}