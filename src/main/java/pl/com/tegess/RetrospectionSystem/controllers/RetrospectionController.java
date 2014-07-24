package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.w3c.dom.html.HTMLDivElement;
import pl.com.tegess.RetrospectionSystem.model.*;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Szymek.
 */
@Controller
public class RetrospectionController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("retrospection")
    public String retrospection(@RequestParam(value = "token") String token,
                                      Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        if(retrospection==null) return "404";
        model.addAttribute("token", token);
        model.addAttribute("author", token);
        if(retrospection.getStatus()) {
            model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList("mad", token));
            model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList("glad", token));
            model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList("newIdea", token));
            model.addAttribute("madStickerList",retrospection.getStickersList("mad", token));
            model.addAttribute("gladStickerList",retrospection.getStickersList("glad", token));
            model.addAttribute("newIdeaStickerList",retrospection.getStickersList("newIdea", token));
            return "retrospection";
        }else{
            model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList("mad", null));
            model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList("glad", null));
            model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList("newIdea", null));
            model.addAttribute("madStickerList",retrospection.getStickersList("mad", null));
            model.addAttribute("gladStickerList",retrospection.getStickersList("glad", null));
            model.addAttribute("newIdeaStickerList",retrospection.getStickersList("newIdea", null));
            return "voteRetrospection";
        }
    }

    @RequestMapping("showRetrospection")
    public String showRetrospection(@RequestParam(value = "id") String id,
                                    Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        if(retrospection==null) return "404";
        model.addAttribute("token", id);
        model.addAttribute("author", id);
        model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList("mad", null));
        model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList("glad", null));
        model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList("newIdea", null));
        model.addAttribute("madStickerList",retrospection.getStickersList("mad", null));
        model.addAttribute("gladStickerList",retrospection.getStickersList("glad", null));
        model.addAttribute("newIdeaStickerList",retrospection.getStickersList("newIdea", null));
        return "showRetrospection";
    }

    @RequestMapping("createRetrospection")
    public String signUp(@RequestParam(value = "author") String author,
                         @RequestParam(value = "question") String question,
                         @RequestParam(value = "membersNumber") Integer membersNumber,
                         Model model) {
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Generator generator = new DefaultGenerator(repository);
        String id = generator.getId();
        List<String> tokens = generator.getTokens(membersNumber);
        tokens.add(id);
        Retrospection retrospection = new Retrospection(id, author, question, tokens);
        repository.insertRetrospection(retrospection);
        return "redirect:retrospectionPanel?id="+ retrospection.getRetrospectionId();
    }

    @RequestMapping("removeRetrospection")
    public String removeRetrospection(@RequestParam(value = "id") String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        repository.removeRetrospection(repository.getRetrospectionById(id));
        return "redirect:";
    }

}
