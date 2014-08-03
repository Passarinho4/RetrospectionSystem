package pl.com.tegess.RetrospectionSystem.model.users;

/**
 * Created by Szymek.
 */
public interface User {

    public String getToken();
    public int getVotesNumber();
    public int getNumberOfVotesForSticker(int id);
    public void addStickerToVoted(int id);
    public void removeStickerFromVoted(int id);
    void setMail(String mail);
    String getMail();
}
