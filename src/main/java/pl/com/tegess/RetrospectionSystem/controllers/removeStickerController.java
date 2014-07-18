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
public class RemoveStickerController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("removeSticker")
    public String removeSticker(@RequestParam(value = "token") String token,
                                @RequestParam(value = "type") String type,
                                @RequestParam(value = "content") String content){

        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        retrospection.removeSticker(retrospection.getStickerByContent(type, content), type);
        repository.modifyRetrospection(retrospection);
        return "redirect:retrospection?token=" + token;
    }

}
