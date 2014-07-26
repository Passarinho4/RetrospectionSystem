package pl.com.tegess.RetrospectionSystem.model;

import com.sun.deploy.panel.ITreeNode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static pl.com.tegess.RetrospectionSystem.model.Type.*;
import static pl.com.tegess.RetrospectionSystem.model.Type.GLAD;
import static pl.com.tegess.RetrospectionSystem.model.Type.MAD;

/**
 * Created by Szymek.
 */
@Document
public class Retrospection {
    @Id
    private final String retrospectionId;
    private final String author;
    private final String question;
    private final List<String> membersTokens;
    private List<Sticker> madStickersList = new ArrayList<Sticker>();
    private List<Sticker> gladStickerList = new ArrayList<Sticker>();
    private List<Sticker> newIdeaStickerList = new ArrayList<Sticker>();
    private boolean status;

    public Retrospection(String retrospectionId, String author, String question, List<String> membersTokens) {
        this.retrospectionId = retrospectionId;
        this.author = author;
        this.question = question;
        this.status = true;
        this.membersTokens = membersTokens;
    }

    public Sticker getStickerByContent(Type type, String content){
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
            if(sticker.getContent().equals(content)) return sticker;
        }
        return null;
    }

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

    public List<String> getMembersTokensList() { return membersTokens; }

    public Integer getMembersNumber() {
        return this.membersTokens.size();
    }

    public String getRetrospectionId() {
        return retrospectionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean containsToken(String token){
        return membersTokens.contains(token);
    }

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

    public String getMembersTokensString(){
        return this.membersTokens.toString();
    }
}
