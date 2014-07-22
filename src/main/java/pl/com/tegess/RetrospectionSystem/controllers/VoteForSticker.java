package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Sticker;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class VoteForSticker {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("voteForSticker")
    public String voteForSticker(@RequestParam(value = "token") String token,
                                 @RequestParam(value = "type") String type,
                                 @RequestParam(value = "content") String content){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        Sticker sticker = retrospection.getStickerByContent(type, content);
        sticker.addVote();
        repository.modifyRetrospection(retrospection);
        return "redirect:retrospection?token=" + token;
    }
}
