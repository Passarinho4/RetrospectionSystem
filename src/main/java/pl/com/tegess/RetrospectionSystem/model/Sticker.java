package pl.com.tegess.RetrospectionSystem.model;

import javax.xml.rpc.holders.IntegerWrapperHolder;

/**
 * Created by Szymek.
 */
public interface Sticker {

    public String getContent();
    public void setContent(String content);
    public String getAuthor();
    public Integer getVotes();
    public void addVote(String token);
    public void removeVote(String token);
    public void setVotes(Integer votes);
    public boolean isComposite();
    public String getShortContent();
    public boolean containsVoteFrom(String token);
    public Integer getId();

}
