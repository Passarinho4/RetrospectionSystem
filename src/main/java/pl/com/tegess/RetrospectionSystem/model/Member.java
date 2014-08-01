package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymek.
 */
@Document
public class Member implements User {

    @Id
    private final String token;
    private List<Integer> votedStickersIds = new ArrayList<Integer>();

    public Member(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public int getVotesNumber() {
        return this.votedStickersIds.size();
    }

    @Override
    public int getNumberOfVotesForSticker(int id) {
        int result = 0;
        for (int i : votedStickersIds){
            if(i==id) result++;
        }
        return result;
    }

    @Override
    public void addStickerToVoted(int id) {
        this.votedStickersIds.add(id);
    }

    @Override
    public void removeStickerFromVoted(int stickerId) {

        this.votedStickersIds.remove(new Integer(stickerId));
    }

}
