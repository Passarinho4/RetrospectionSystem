package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Sticker;
import pl.com.tegess.RetrospectionSystem.model.StickerLeaf;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class AddStickerController {

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
}
