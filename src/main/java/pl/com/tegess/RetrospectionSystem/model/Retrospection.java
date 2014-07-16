package pl.com.tegess.RetrospectionSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    private final Integer membersNumber;
    private List<Sticker> madStickersList = new ArrayList<Sticker>();
    private List<Sticker> gladStickerList = new ArrayList<Sticker>();
    private List<Sticker> newIdeaStickerList = new ArrayList<Sticker>();
    private boolean status;


    public Retrospection(String retrospectionId, String author, String question, Integer membersNumber) {
        this.retrospectionId = retrospectionId;
        this.author = author;
        this.question = question;
        this.membersNumber = membersNumber;
        this.status = true;
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

    public Integer getMembersNumber() {
        return membersNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
