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
public class RetrospectionController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("retrospection")
    public String authorRetrospection(@RequestParam(value = "token") String token,
                                      Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        if(retrospection==null) return "404";
        model.addAttribute("token", token);
        model.addAttribute("author", retrospection.getAuthor());
        model.addAttribute("madStickerList",retrospection.getMadStickersList());
        model.addAttribute("gladStickerList",retrospection.getGladStickerList());
        model.addAttribute("newIdeaStickerList",retrospection.getNewIdeaStickerList());
        return "retrospection";
    }

}
