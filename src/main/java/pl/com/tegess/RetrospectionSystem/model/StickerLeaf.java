package pl.com.tegess.RetrospectionSystem.model;

/**
 * Created by Szymek.
 */
public class StickerLeaf implements Sticker {

    private final String content;
    private final String author;

    public StickerLeaf(String content, String author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
