package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.*;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class StickerController {

    @Autowired
    ConfigurableApplicationContext applicationContext;


    @RequestMapping("addSticker")
    public String addSticker(@RequestParam(value = "token", required = false) String token,
                             @RequestParam(value = "id", required = false) String id,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "author") String author,
                             @RequestParam(value = "type") Type type,
                             Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        Sticker sticker = new StickerLeaf(content, author);
        retrospection.addSticker(sticker, type);
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("removeSticker")
    public String removeSticker(@RequestParam(value = "token", required = false) String token,
                                @RequestParam(value = "id", required = false) String id,
                                @RequestParam(value = "type") Type type,
                                @RequestParam(value = "content") String content){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null){
            retrospection = repository.getRetrospectionByToken(token);
        }else {
            retrospection = repository.getRetrospectionById(id);
        }
        retrospection.removeSticker(retrospection.getStickerByContent(type, content), type);
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("makeStickerComposite")
    public String makeStickerComposite(@RequestParam(value = "token", required = false) String token,
                                       @RequestParam(value = "id", required = false) String id,
                                       @RequestParam(value = "type") Type type,
                                       @RequestParam(value = "content") String content){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        Sticker sticker = retrospection.getStickerByContent(type, content);
        Sticker stickerComposite = new StickerComposite(sticker.getContent(), sticker.getAuthor());
        stickerComposite.setVotes(sticker.getVotes());
        retrospection.removeSticker(sticker, type);
        retrospection.addSticker(stickerComposite, type);
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("addStickerLeafToStickerComposite")
    public String addStickerLeafToStickerComposite(@RequestParam(value = "token", required = false) String token,
                                                   @RequestParam(value = "id", required = false) String id,
                                                   @RequestParam(value = "compositeContent") String compositeContent,
                                                   @RequestParam(value = "compositeType") Type compositeType,
                                                   @RequestParam(value = "leafContent") String leafContent,
                                                   @RequestParam(value = "leafType") Type leafType){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerByContent(compositeType, compositeContent);
        Sticker stickerLeaf = retrospection.getStickerByContent(leafType, leafContent);
        stickerComposite.setVotes(stickerComposite.getVotes()+stickerLeaf.getVotes());
        stickerComposite.addChild(stickerLeaf);
        retrospection.removeSticker(stickerLeaf, leafType);
        repository.modifyRetrospection(retrospection);        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("removeStickerLeaf")
    public String removeStickerLeaf(@RequestParam(value = "compositeStickerContent") String compositeStickerContent,
                                    @RequestParam(value = "leafStickerContent") String leafStickerContent,
                                    @RequestParam(value = "type") Type type,
                                    @RequestParam(value = "token", required = false) String token,
                                    @RequestParam(value = "id", required = false) String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerByContent(type, compositeStickerContent);
        stickerComposite.removeChild(stickerComposite.getChild(leafStickerContent));
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }
}
