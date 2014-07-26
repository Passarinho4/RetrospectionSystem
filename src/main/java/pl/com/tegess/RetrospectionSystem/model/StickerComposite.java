package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Szymek.
 */

public class StickerComposite implements Sticker, Iterable {


    private final String content;
    private final String author;
    private Integer votes;
    private List<String> voted = new ArrayList<String>();

    private List<Sticker> stickerList = new ArrayList<Sticker>();

    public StickerComposite(String content, String author) {
        this.content = content;
        this.author = author;
        this.votes = 0;
    }

    public void addChild(Sticker child){
        stickerList.add(child);
    }

    public void removeChild(Sticker child){
        stickerList.remove(child);
    }

    public Sticker getChild(String content) {
        Sticker sticker;
        Iterator iterator = iterator();
        while(iterator().hasNext()){
            sticker = (Sticker)iterator.next();
            if(sticker.getContent().equals(content)) return sticker;
        }
        return null;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public Integer getVotes() {
        return votes;
    }

    @Override
    public void addVote(String token) {
        this.voted.add(token);
        this.votes++;
    }

    @Override
    public void removeVote(String token) {
        this.voted.remove(token);
        this.votes--;
    }

    @Override
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public String getShortContent() {
        return this.content.substring(0, Math.min(20, this.content.length())) + "..."; //first 10 chars of content;
    }

    @Override
    public boolean canVote(String token) {
        return !this.voted.contains(token);
    }

    @Override
    public Iterator iterator() {
        return stickerList.iterator();
    }

    public List<Sticker> getChildren() { return stickerList;}

}
