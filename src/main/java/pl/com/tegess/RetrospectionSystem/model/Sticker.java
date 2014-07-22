package pl.com.tegess.RetrospectionSystem.model;

import javax.xml.rpc.holders.IntegerWrapperHolder;

/**
 * Created by Szymek.
 */
public interface Sticker {

    public String getContent();
    public String getAuthor();
    public Integer getVotes();
    public void addVote();
    public boolean isComposite();
    public String getShortContent();

}
