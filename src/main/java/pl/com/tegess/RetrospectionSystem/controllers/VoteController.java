package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Sticker;
import pl.com.tegess.RetrospectionSystem.model.Type;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class VoteController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("voteForSticker")
    public String voteForSticker(@RequestParam(value = "token") String token,
                                 @RequestParam(value = "type") Type type,
                                 @RequestParam(value = "stickerId") Integer stickerId){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        Sticker sticker = retrospection.getStickerById(type, stickerId);
        sticker.addVote(token);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token=" + token;
    }

    @RequestMapping("RemoveVoteForSticker")
    public String RemoveVoteForSticker(@RequestParam(value = "token") String token,
                                       @RequestParam(value = "type") Type type,
                                       @RequestParam(value = "stickerId") Integer stickerId){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        Sticker sticker = retrospection.getStickerById(type, stickerId);
        sticker.removeVote(token);
        repository.modifyRetrospection(retrospection);
        if(token.equals(retrospection.getRetrospectionId())) return "redirect:showRetrospection?id="+token;
        return "redirect:retrospection?token=" + token;
    }
}
