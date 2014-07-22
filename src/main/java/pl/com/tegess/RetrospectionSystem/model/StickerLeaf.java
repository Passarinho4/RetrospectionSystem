package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public class StickerLeaf implements Sticker {

    private final String content;
    private final String author;
    private Integer votes;

    public StickerLeaf(String content, String author) {
        this.content = content;
        this.author = author;
        this.votes = 0;
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
    public void addVote() {
        this.votes++;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public String getShortContent() {
        return this.content; //first 10 chars of content;
    }
}
