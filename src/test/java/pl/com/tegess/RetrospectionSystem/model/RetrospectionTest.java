package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RetrospectionTest {

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
        //Sticker sticker = new StickerLeaf("content", "author");

        //when
       // underTest.addSticker();

    }

}