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
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class MakeStickerComposite {

    @Autowired
    ConfigurableApplicationContext applicationContext;

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
}
