package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
public class AddStickerLeafToStickerCompositeController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

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
        stickerComposite.addChild(stickerLeaf);
        retrospection.removeSticker(stickerLeaf, leafType);
        repository.modifyRetrospection(retrospection);
        return "redirect:rretrospection?token="+token;
    }
}
