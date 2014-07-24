package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Sticker;
import pl.com.tegess.RetrospectionSystem.model.StickerComposite;
import pl.com.tegess.RetrospectionSystem.model.StickerLeaf;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class StickerController {

    @Autowired
    ConfigurableApplicationContext applicationContext;


    @RequestMapping("addSticker")
    public String addSticker(@RequestParam(value = "token") String token,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "author") String author,
                             @RequestParam(value = "type") String type,
                             Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);

        Retrospection retrospection = repository.getRetrospectionByToken(token);
        Sticker sticker = new StickerLeaf(content, author);
        retrospection.addSticker(sticker, type);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token=" + token;
    }

    @RequestMapping("removeSticker")
    public String removeSticker(@RequestParam(value = "token") String token,
                                @RequestParam(value = "type") String type,
                                @RequestParam(value = "content") String content){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);

        Retrospection retrospection = repository.getRetrospectionByToken(token);
        retrospection.removeSticker(retrospection.getStickerByContent(type, content), type);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token=" + token;
    }

    @RequestMapping("makeStickerComposite")
    public String makeStickerComposite(@RequestParam(value = "token") String token,
                                       @RequestParam(value = "type") String type,
                                       @RequestParam(value = "content") String content){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);

        Retrospection retrospection = repository.getRetrospectionByToken(token);
        Sticker sticker = retrospection.getStickerByContent(type, content);
        Sticker stickerComposite = new StickerComposite(sticker.getContent(), sticker.getAuthor());
        stickerComposite.setVotes(sticker.getVotes());
        retrospection.removeSticker(sticker, type);
        retrospection.addSticker(stickerComposite, type);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token="+token;
    }

    @RequestMapping("addStickerLeafToStickerComposite")
    public String addStickerLeafToStickerComposite(@RequestParam(value = "token") String token,
                                                   @RequestParam(value = "compositeContent") String compositeContent,
                                                   @RequestParam(value = "compositeType") String compositeType,
                                                   @RequestParam(value = "leafContent") String leafContent,
                                                   @RequestParam(value = "leafType") String leafType){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);

        Retrospection retrospection = repository.getRetrospectionByToken(token);
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerByContent(compositeType, compositeContent);
        Sticker stickerLeaf = retrospection.getStickerByContent(leafType, leafContent);
        stickerComposite.setVotes(stickerComposite.getVotes()+stickerLeaf.getVotes());
        stickerComposite.addChild(stickerLeaf);
        retrospection.removeSticker(stickerLeaf, leafType);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token="+token;
    }

    @RequestMapping("removeStickerLeaf")
    public String removeStickerLeaf(@RequestParam(value = "compositeStickerContent") String compositeStickerContent,
                                    @RequestParam(value = "leafStickerContent") String leafStickerContent,
                                    @RequestParam(value = "type") String type,
                                    @RequestParam(value = "token") String token){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerByContent(type, compositeStickerContent);
        stickerComposite.removeChild(stickerComposite.getChild(leafStickerContent));
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token=" + token;
    }
}
