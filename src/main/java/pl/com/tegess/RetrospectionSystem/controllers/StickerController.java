package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.*;
import pl.com.tegess.RetrospectionSystem.repository.CounterRepository;
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
        CounterRepository counterRepository = applicationContext.getBean(CounterRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        Sticker sticker = new StickerLeaf(counterRepository.getNextSequence("Sticker"), content, author);
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
                                @RequestParam(value = "stickerId") Integer stickerId){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null){
            retrospection = repository.getRetrospectionByToken(token);
        }else {
            retrospection = repository.getRetrospectionById(id);
        }
        retrospection.removeSticker(retrospection.getStickerById(type, stickerId), type);
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
                                       @RequestParam(value = "stickerId") Integer stickerId){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        CounterRepository counterRepository = applicationContext.getBean(CounterRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        Sticker sticker = retrospection.getStickerById(type, stickerId);
        Sticker stickerComposite = new StickerComposite(counterRepository.getNextSequence("Sticker"),
                sticker.getContent(), sticker.getAuthor());
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
                                                   @RequestParam(value = "compositeId") Integer compositeId,
                                                   @RequestParam(value = "compositeType") Type compositeType,
                                                   @RequestParam(value = "leafId") Integer leafId,
                                                   @RequestParam(value = "leafType") Type leafType){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerById(compositeType, compositeId);
        Sticker stickerLeaf = retrospection.getStickerById(leafType, leafId);
        stickerComposite.setVotes(stickerComposite.getVotes()+stickerLeaf.getVotes());
        stickerComposite.addChild(stickerLeaf);
        retrospection.removeSticker(stickerLeaf, leafType);
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("removeStickerLeaf")
    public String removeStickerLeaf(@RequestParam(value = "compositeStickerId") Integer compositeStickerId,
                                    @RequestParam(value = "leafStickerId") Integer leafStickerId,
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
        StickerComposite stickerComposite = (StickerComposite)retrospection.getStickerById(type, compositeStickerId);
        stickerComposite.removeChild(stickerComposite.getChild(leafStickerId));
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("editSticker")
    public String editSticker(@RequestParam(value = "stickerId") Integer stickerId,
                              @RequestParam(value = "type") Type type,
                              @RequestParam(value = "newContent") String newContent,
                              @RequestParam(value = "token", required = false) String token,
                              @RequestParam(value = "id", required = false)String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
        }else{
            retrospection = repository.getRetrospectionById(id);
        }
        Sticker sticker = retrospection.getStickerById(type, stickerId);
        sticker.setContent(newContent);
        repository.modifyRetrospection(retrospection);
        if(token!=null) {
            return "redirect:retrospection?token=" + token;
        }
        return "redirect:showRetrospection?id="+id;
    }

    @RequestMapping("editStickerForm")
    public String editStickerForm(@RequestParam(value = "stickerId") Integer stickerId,
                                  @RequestParam(value = "type") Type type,
                                  @RequestParam(value = "token", required = false) String token,
                                  @RequestParam(value = "id", required = false) String id,
                                  Model model){

        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection;
        if(token!=null) {
            retrospection = repository.getRetrospectionByToken(token);
            model.addAttribute("token", token);
        }else{
            retrospection = repository.getRetrospectionById(id);
            model.addAttribute("id", id);
        }
        model.addAttribute("stickerId", stickerId);
        Sticker sticker = retrospection.getStickerById(type, stickerId);
        model.addAttribute("stickerContent", sticker.getContent());
        model.addAttribute("type", type);
        return "editStickerForm";
    }

}
