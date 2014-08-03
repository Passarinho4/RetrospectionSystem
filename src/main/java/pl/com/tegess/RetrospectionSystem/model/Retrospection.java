package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.stickers.StickerComposite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representation of Retrospection in Mad Glad New Idea Retrospective.
 * Represents one retrospection, contains mad, glad and new idea stickers lists,
 * has author, question (which describes retrospection) and some vote strategy.
 *
 * @author Szymek
 *
 * @version 1.0
 */
@Document
public class Retrospection {

    @Id
    /**
     * The retrospection id. Has to be unique.
     */
    private final String retrospectionId;

    /**
     * The retrospection author.
     */
    private final String author;

    /**
     * The retrospection question or short description.
     */
    private final String question;

    /**
     * The retrospection members tokens list.
     */
    private final List<String> membersTokens;

    /**
     * The retrospection mad stickers list.
     */
    private List<Sticker> madStickersList = new ArrayList<Sticker>();

    /**
     * The retrospection glad stickers list.
     */
    private List<Sticker> gladStickerList = new ArrayList<Sticker>();

    /**
     * The new idea stickers list.
     */
    private List<Sticker> newIdeaStickerList = new ArrayList<Sticker>();

    /**
     * The retrospection status.
     */
    private boolean status;

    /**
     * The retrospection vote strategy.
     */
    private String voteStrategyClassName;

    /**
     * Constructor sets retrospection id, author, question, members tokens
     * and default value of vote strategy (One for one).
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.OneForOneVoteStrategy
     *
     * @param retrospectionId the retrospection id.
     * @param author the retrospection author.
     * @param question the retrospection question or short description.
     * @param membersTokens the members tokens list.
     */
    public Retrospection(String retrospectionId, String author, String question, List<String> membersTokens) {
        this.retrospectionId = retrospectionId;
        this.author = author;
        this.question = question;
        this.status = true;
        this.membersTokens = membersTokens;
        this.voteStrategyClassName = "pl.com.tegess.RetrospectionSystem.model.voteStrategy.OneForOneVoteStrategy";
    }

    /**
     * Returns sticker using sticker id. Param type is required,
     * because it works faster.
     * @see pl.com.tegess.RetrospectionSystem.model.Type
     * @param type the sticker type.
     * @param id the sticker id.
     * @return the right sticker.
     */
    public Sticker getStickerById(Type type, Integer id){
        Iterator iterator;
        switch (type){
            case MAD: iterator = this.madStickersList.iterator();
                break;
            case GLAD: iterator = this.gladStickerList.iterator();
                break;
            case NEWIDEA: iterator = this.newIdeaStickerList.iterator();
                break;
            default: iterator=null;
                break;
        }
        Sticker sticker;
        while (iterator.hasNext()){
            sticker = (Sticker)iterator.next();
            if(sticker.getId().equals(id)) return sticker;
        }
        return null;
    }

    /**
     * Removes right sticker from retrospection.
     * @see pl.com.tegess.RetrospectionSystem.model.Type
     * @param sticker the sticker to remove.
     * @param type the sticker type.
     */
    public void removeSticker(Sticker sticker, Type type) {
        switch (type){
            case MAD: this.madStickersList.remove(sticker);
                break;
            case GLAD: this.gladStickerList.remove(sticker);
                break;
            case NEWIDEA: this.newIdeaStickerList.remove(sticker);
                break;
            default:
                break;
        }
    }

    /**
     * Adds sticker to retrospection.
     * @see pl.com.tegess.RetrospectionSystem.model.Type
     * @param sticker the sticker to add.
     * @param type the sticker type.
     */
    public void addSticker(Sticker sticker, Type type){
        switch (type){
            case MAD : this.madStickersList.add(sticker);
                break;
            case GLAD: this.gladStickerList.add(sticker);
                break;
            case NEWIDEA: this.newIdeaStickerList.add(sticker);
                break;
            default: break;
        }
    }

    /**
     * Returns stickers list of right member.
     *
     * @see pl.com.tegess.RetrospectionSystem.model.users.User
     * @see pl.com.tegess.RetrospectionSystem.model.Type
     * @param type the sticker type.
     * @param token the member token.
     * @return the stickers list.
     */
    public List<Sticker> getStickersList(Type type, String token) {
        if(token==null){
            switch (type){
                case MAD: return madStickersList;
                case GLAD: return gladStickerList;
                case NEWIDEA: return newIdeaStickerList;
            }
        }
        Iterator<Sticker> iterator = null;
        List<Sticker> list = new ArrayList<Sticker>();
        switch (type){
            case MAD: iterator  = madStickersList.iterator();
                break;
            case GLAD: iterator = gladStickerList.iterator();
                break;
            case NEWIDEA: iterator = newIdeaStickerList.iterator();
                break;
        }

        Sticker sticker;
        while (iterator.hasNext()){
            sticker = iterator.next();
            if(sticker.getAuthor().equals(token)) list.add(sticker);
        }
        return list;
    }

    /**
     * Returns members tokens list. This list contains tokens of every user
     * who can add stickers and vote in this retrospection.
     *
     * @return the members tokens list.
     */
    public List<String> getMembersTokensList() { return membersTokens; }

    /**
     * Returns members number.
     *
     * @return the members number.
     */
    public Integer getMembersNumber() {
        return this.membersTokens.size();
    }

    /**
     * Returns retrospection id.
     * @return the retrospection id.
     */
    public String getRetrospectionId() {
        return retrospectionId;
    }

    /**
     * Returns retrospection question or short description.
     * @return the retrospection question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns retrospection author.
     * @return the retrospection author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns retrospection status.
     * @return the retrospection status.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Sets the retrospection status. True if members can add, edit and remove stickers.
     * False if members should vote.
     * @param status the retrospection status.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Returns true if retrospection contains member with param token.
     * Returns false if retrospection doesn't contain member with this token.
     * @param token the member token.
     * @return true or false.
     */
    public boolean containsToken(String token){
        return membersTokens.contains(token);
    }

    /**
     * Returns all parents stickers list of right type.
     * @see pl.com.tegess.RetrospectionSystem.model.Type
     * @see pl.com.tegess.RetrospectionSystem.model.stickers.StickerComposite
     * @see pl.com.tegess.RetrospectionSystem.model.users.User
     * @param type the stickers type.
     * @param token the user token.
     * @return sticker composite list.
     */
    public List<StickerComposite> getCompositeStickersList(Type type, String token) {
        List<StickerComposite> compositeStickersList = new ArrayList<StickerComposite>();
        List<Sticker> stickerList = getStickersList(type, token);
        Iterator iterator = stickerList.iterator();
        Sticker sticker;
        while (iterator.hasNext()){
            sticker = (Sticker)iterator.next();
            if (sticker.isComposite()) compositeStickersList.add((StickerComposite)sticker);
        }
        return compositeStickersList;
    }

    /**
     * Returns members tokens list as string.
     * @return the members tokens string.
     */
    public String getMembersTokensString(){
        return this.membersTokens.toString();
    }

    /**
     * Returns retrospection vote strategy class name. (Without packages).
     * @return the retrospection vote strategy class name.
     */
    public String getVoteStrategyClassName() {
        return voteStrategyClassName;
    }

    /**
     * Sets retrospection vote strategy class name. (Without packages).
     * Strategy class have to implement VoteStrategy interface.
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.OneForOneVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.FreeVoteStrategy
     * @see pl.com.tegess.RetrospectionSystem.model.voteStrategy.LimitedVotesPerMemberVoteStrategy
     * @param voteStrategyClassName the vote strategy class name.
     */
    public void setVoteStrategyClassName(String voteStrategyClassName) {
        this.voteStrategyClassName = voteStrategyClassName;
    }
}
