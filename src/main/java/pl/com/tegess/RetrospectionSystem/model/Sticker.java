package pl.com.tegess.RetrospectionSystem.model;

/**
 * Represent every sticker in RetrospectionSystem.
 * Stickers use composite pattern and this interface
 * declares methods which every sticker should have.
 *
 * @author Szymek
 *
 * @version 1.0
 */
public interface Sticker {

    /**
     * Returns sticker content.
     * @return the sticker content.
     */
    public String getContent();

    /**
     * Sets sticker content.
     * @param content the new content.
     */
    public void setContent(String content);

    /**
     * Returns sticker author token.
     * @return the author token.
     */
    public String getAuthor();

    /**
     * Returns number of sticker votes.
     * @return the number of votes.
     */
    public Integer getVotes();

    /**
     * Adds user token to list of users who voted for this sticker.
     * @see pl.com.tegess.RetrospectionSystem.model.User
     *
     * @param token the user token.
     */
    public void addVote(String token);

    /**
     * Removes user token from list of users who voted for this sticker.
     * @see pl.com.tegess.RetrospectionSystem.model.User
     *
     * @param token the user token.
     */
    public void removeVote(String token);

    /**
     * Sets votes number.
     * @param votes the votes number.
     */
    public void setVotes(Integer votes);

    /**
     * Returns true if sticker is instance of StickerComposite.
     * Returns false if sticker is instance of StickerLeaf.
     * @see pl.com.tegess.RetrospectionSystem.model.StickerComposite
     * @see pl.com.tegess.RetrospectionSystem.model.StickerLeaf
     *
     * @return true or false.
     */
    public boolean isComposite();

    /**
     * Returns short content of sticker. Short content means 20 chars and '...' at the end.
     *
     * @return the short content.
     */
    public String getShortContent();

    /**
     * Returns true if user voted for this token. False if user didn't vote for
     * this token.
     * @see pl.com.tegess.RetrospectionSystem.model.User
     *
     * @param token the user token.
     * @return true or false.
     */
    public boolean containsVoteFrom(String token);

    /**
     * Returns sticker id.
     * @return the sticker id.
     */
    public Integer getId();

}
