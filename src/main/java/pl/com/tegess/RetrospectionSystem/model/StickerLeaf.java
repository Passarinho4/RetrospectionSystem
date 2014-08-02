package pl.com.tegess.RetrospectionSystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents sticker child and implements Sticker interface.
 */

public class StickerLeaf implements Sticker {

    /**
     * The sticker unique id.
     */
    private final int id;

    /**
     * The sticker content.
     */
    private String content;

    /**
     * The sticker author.
     */
    private final String author;

    /**
     * The sticker votes number.
     */
    private Integer votes;

    /**
     * The list of users ids, who voted for this sticker.
     */
    private List<String> voted = new ArrayList<String>();

    /**
     * Constructor sets values and create new StickerLeaf object.
     * @param id the sticker id.
     * @param content the sticker content.
     * @param author the sticker author.
     */
    public StickerLeaf(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.votes = 0;

    }

    @Override
    public void setVotes(Integer votes) {
        this.votes = votes;
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
        return this.votes;
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
    public boolean isComposite() {
        return false;
    }

    @Override
    public String getShortContent() {
        return this.content.substring(0, Math.min(20, this.content.length())) + "..."; //first 20 chars of content;
    }

    @Override
    public boolean containsVoteFrom(String token) {
        return this.voted.contains(token);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
