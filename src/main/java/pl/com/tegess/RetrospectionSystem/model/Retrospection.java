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

    public List<Sticker> getStickersList(String type, String token) {
        if(token==null){
            switch (type){
                case "mad": return madStickersList;
                case "glad": return gladStickerList;
                case "newIdea": return newIdeaStickerList;
            }
        }
        Iterator<Sticker> iterator = null;
        List<Sticker> list = new ArrayList<Sticker>();
        switch (type){
            case "mad": iterator  = madStickersList.iterator();
                break;
            case "glad": iterator = gladStickerList.iterator();
                break;
            case "newIdea": iterator = newIdeaStickerList.iterator();
                break;
        }

        Sticker sticker;
        while (iterator!= null && iterator.hasNext()){
            sticker = iterator.next();
            if(sticker.getAuthor().equals(token)) list.add(sticker);
        }
        return list;
    }

    public List<String> getMembersTokensList() { return membersTokens; }

    public Integer getMembersNumber() {
        return this.membersTokens.size()-1;
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

    public List<StickerComposite> getCompositeStickersList(String type, String token) {
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
