package pl.com.tegess.RetrospectionSystem.model.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.com.tegess.RetrospectionSystem.model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Member class implements User interface and represents normal user
 * of Retrospection System. It contains user token, which is something
 * like user id. It also has list which storage for which stickers this
 * user has voted.
 *
 * @author Szymek
 *
 * @version 1.0
 *
 */
@Document
public class Member implements User {

    @Id
    /**
     * The token of user.
     */
    private final String token;

    /**
     * The user mail.
     */
    private String mail;
    /**
     * The list, which contains ids of these stickers for which user has voted.
     */
    private List<Integer> votedStickersIds;

    /**
     * Default constructor for Member class. Creates new Member object,
     * with token given as param and with empty votes list. Default mail
     * address is null.
     *
     * @param token the token to be set
     */
    public Member(String token) {
        this.token = token;
        this.votedStickersIds = new ArrayList<Integer>();
        this.mail = null;
    }

    /**
     * Returns the token of this user. Token is final,
     * so this method returns always this token, which was
     * set in constructor.
     *
     * @return the user token
     */
    @Override
    public String getToken() {
        return this.token;
    }

    /**
     * Returns the number of added votes.
     *
     * @return the number of user votes.
     */
    @Override
    public int getVotesNumber() {
        return this.votedStickersIds.size();
    }

    /**
     * Returs the number of added votes for sticker with id,
     * which is passed as param.
     * @param id sticker id.
     * @return the number od votes for sticker.
     */
    @Override
    public int getNumberOfVotesForSticker(int id) {
        int result = 0;
        for (int i : votedStickersIds){
            if(i==id) result++;
        }
        return result;
    }

    /**
     * Adds sticker id to voted.
     * @param stickerId sticker id.
     */
    @Override
    public void addStickerToVoted(int stickerId) {
        this.votedStickersIds.add(stickerId);
    }

    /**
     * Removes sticker id from voted.
     * @param stickerId sticker id.
     */
    @Override
    public void removeStickerFromVoted(int stickerId) {

        this.votedStickersIds.remove(new Integer(stickerId));
    }

    /**
     * Sets user mail address.
     * @param mail the mail address.
     */
    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Returns user mail address.
     * @return mail address.
     */
    @Override
    public String getMail() {
        return this.mail;
    }

}
