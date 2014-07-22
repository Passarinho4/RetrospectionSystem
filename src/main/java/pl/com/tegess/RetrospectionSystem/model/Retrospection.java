package pl.com.tegess.RetrospectionSystem.model;

import com.sun.deploy.panel.ITreeNode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public Sticker getStickerByContent(String type, String content){
        Iterator iterator;
        switch (type){
            case "mad": iterator = this.madStickersList.iterator();
                break;
            case "glad": iterator = this.gladStickerList.iterator();
                break;
            case "newIdea": iterator = this.newIdeaStickerList.iterator();
                break;
            default: iterator=null;
                break;
        }
        Sticker sticker;
        while (iterator!= null && iterator.hasNext()){
            sticker = (Sticker)iterator.next();
            if(sticker.getContent().equals(content)) return sticker;
        }
        return null;
    }

    public void removeSticker(Sticker sticker, String type) {
        switch (type){
            case "mad": this.madStickersList.remove(sticker);
                break;
            case "glad": this.gladStickerList.remove(sticker);
                break;
            case "newIdea": this.newIdeaStickerList.remove(sticker);
                break;
            default:
                break;
        }
    }

    public void addSticker(Sticker sticker, String type){
        switch (type){
            case "mad" : this.madStickersList.add(sticker);
                break;
            case "glad" : this.gladStickerList.add(sticker);
                break;
            case "newIdea" : this.newIdeaStickerList.add(sticker);
                break;
            default: break;
        }
    }

    public List<Sticker> getMadStickersList() {
        return madStickersList;
    }

    public List<String> getMembersTokensList() { return membersTokens; }

    public List<Sticker> getGladStickerList() {
        return gladStickerList;
    }

    public List<Sticker> getNewIdeaStickerList() {
        return newIdeaStickerList;
    }

    public String getMembersTokensString() {
        return this.membersTokens.toString();
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

    public List<StickerComposite> getCompositeStickersList(String type) {
        List<StickerComposite> compositeStickersList = new ArrayList<StickerComposite>();
        Iterator iterator;
        switch (type){
            case "mad": iterator = this.madStickersList.iterator();
                break;
            case "glad" : iterator = this.gladStickerList.iterator();
                break;
            case "newIdea" : iterator = this.newIdeaStickerList.iterator();
                break;
            default: iterator = null;
        }
        Sticker sticker;
        while (iterator!= null && iterator.hasNext()){
            sticker = (Sticker)iterator.next();
            if (sticker.isComposite()) compositeStickersList.add((StickerComposite)sticker);
        }
        return compositeStickersList;
    }
}
