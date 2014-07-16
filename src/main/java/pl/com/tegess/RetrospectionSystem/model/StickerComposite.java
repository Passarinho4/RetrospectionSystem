package pl.com.tegess.RetrospectionSystem.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Szymek.
 */
public class StickerComposite implements Sticker, Iterable {

    private final String content;
    private final String author;

    private List<Sticker> stickerList = new ArrayList<Sticker>();

    public StickerComposite(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void addChild(Sticker child){
        stickerList.add(child);
    }

    public void removeChild(Sticker child){
        stickerList.remove(child);
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
    public Iterator iterator() {
        return stickerList.iterator();
    }
}
