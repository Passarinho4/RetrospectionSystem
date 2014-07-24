package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
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
public class RemoveStickerLeafController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

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
