package pl.com.tegess.RetrospectionSystem.model.stickers;

import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents composite sticker and implements Sticker interface.
 *
 * @author Szymek
 *
 * @version 1.0
 */

public class StickerComposite implements Sticker, Iterable {

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
     * The sticker children list.
     */
    private List<Sticker> stickerList = new ArrayList<Sticker>();

    /**
     * Constructor sets values and creates new StickerComposite object.
     * @param id the sticker id.
     * @param content the sticker content.
     * @param author the sticker author.
     */
    public StickerComposite(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.votes = 0;
    }

    /**
     * Adds Sticker to stickers children list.
     * @param child the sticker.
     */
    public void addChild(Sticker child){
        stickerList.add(child);
    }

    /**
     * Removes Sticker from stickers children list.
     * @param child the sticker.
     */
    public void removeChild(Sticker child){
        stickerList.remove(child);
    }

    /**
     * Returns right sticker from stickers children list.
     * If right sticker does not exist returns null.
     * @param id the sticker id.
     * @return the sticker or null.
     */
    public Sticker getChild(Integer id) {
        Sticker sticker;
        Iterator iterator = iterator();
        while(iterator().hasNext()){
            sticker = (Sticker)iterator.next();
            if(sticker.getId().equals(id)) return sticker;
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
    public boolean containsVoteFrom(String token) {
        return this.voted.contains(token);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public Iterator iterator() {
        return stickerList.iterator();
    }

    public List<Sticker> getChildren() { return stickerList;}

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
