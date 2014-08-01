package pl.com.tegess.RetrospectionSystem.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LimitedVotesPerMemberVoteStrategyTest {

    @Test
    public void testDefaultConstructorCanVote(){

        //given
        LimitedVotesPerMemberVoteStrategy underTest = new LimitedVotesPerMemberVoteStrategy();
        Member member = new Member("token");
        Sticker sticker = new StickerLeaf(111, "content", "author");

        //when
        for(int i=0; i<9; i++){
            member.addStickerToVoted(i);
        }

        //then
        Assert.assertEquals(true, underTest.canVote(member, sticker));

        //when
        member.addStickerToVoted(10);

        //then
        Assert.assertEquals(false, underTest.canVote(member, sticker));
    }

    @Test
    public void testExtendedConstructorCanVote() {

        //given
        LimitedVotesPerMemberVoteStrategy underTest = new LimitedVotesPerMemberVoteStrategy(5);
        Member member = new Member("token");
        Sticker sticker = new StickerLeaf(111, "content", "author");

        //when
        for(int i=0; i<4; i++){
            member.addStickerToVoted(i);
        }

        //then
        Assert.assertEquals(true, underTest.canVote(member, sticker));

        //when
        member.addStickerToVoted(5);

        //then
        Assert.assertEquals(false, underTest.canVote(member, sticker));
    }

}