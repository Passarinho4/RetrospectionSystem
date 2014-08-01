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
import pl.com.tegess.RetrospectionSystem.repository.UserRepository;

import javax.jws.soap.SOAPBinding;
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
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Retrospection retrospection = repository.getRetrospectionByToken(token);
        if(retrospection==null) return "404";
        model.addAttribute("token", token);
        model.addAttribute("author", token);
        if(retrospection.getStatus()) {
            model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList(Type.MAD, token));
            model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList(Type.GLAD, token));
            model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList(Type.NEWIDEA, token));
            model.addAttribute("madStickerList",retrospection.getStickersList(Type.MAD, token));
            model.addAttribute("gladStickerList",retrospection.getStickersList(Type.GLAD, token));
            model.addAttribute("newIdeaStickerList",retrospection.getStickersList(Type.NEWIDEA, token));
            return "retrospection";
        }else{
            VoteStrategy voteStrategy = createVoteStrategy(retrospection.getVoteStrategyClassName());
            model.addAttribute("voteStrategy", voteStrategy);
            model.addAttribute("user", userRepository.getUserByToken(token));
            model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList(Type.MAD, null));
            model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList(Type.GLAD, null));
            model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList(Type.NEWIDEA, null));
            model.addAttribute("madStickerList",retrospection.getStickersList(Type.MAD, null));
            model.addAttribute("gladStickerList",retrospection.getStickersList(Type.GLAD, null));
            model.addAttribute("newIdeaStickerList",retrospection.getStickersList(Type.NEWIDEA, null));
            return "voteRetrospection";
        }
    }

    private VoteStrategy createVoteStrategy(String voteStrategyClassName) {
        Class c = null;
        try {
            c = Class.forName("pl.com.tegess.RetrospectionSystem.model."+ voteStrategyClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (VoteStrategy)o;
    }

    @RequestMapping("showRetrospection")
    public String showRetrospection(@RequestParam(value = "id") String id,
                                    Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        if(retrospection==null) return "404";
        model.addAttribute("id", id);
        model.addAttribute("author", id);
        model.addAttribute("madCompositeStickersList", retrospection.getCompositeStickersList(Type.MAD, null));
        model.addAttribute("gladCompositeStickersList", retrospection.getCompositeStickersList(Type.GLAD, null));
        model.addAttribute("newIdeaCompositeStickersList", retrospection.getCompositeStickersList(Type.NEWIDEA, null));
        model.addAttribute("madStickerList",retrospection.getStickersList(Type.MAD, null));
        model.addAttribute("gladStickerList",retrospection.getStickersList(Type.GLAD, null));
        model.addAttribute("newIdeaStickerList",retrospection.getStickersList(Type.NEWIDEA, null));
        return "showRetrospection";
    }

    @RequestMapping("createRetrospection")
    public String signUp(@RequestParam(value = "author") String author,
                         @RequestParam(value = "question") String question,
                         @RequestParam(value = "membersNumber") Integer membersNumber,
                         Model model) {
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Generator generator = new DefaultGenerator(repository);
        String id = generator.getId();
        List<String> tokens = generator.getTokens(membersNumber);
        User user;
        for(String t: tokens){
            user = new Member(t);
            userRepository.insertUser(user);
        }
        Retrospection retrospection = new Retrospection(id, author, question, tokens);
        repository.insertRetrospection(retrospection);
        return "redirect:retrospectionPanel?id="+ retrospection.getRetrospectionId();
    }

    @RequestMapping("removeRetrospection")
    public String removeRetrospection(@RequestParam(value = "id") String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        userRepository.removeAllUsers(retrospection.getMembersTokensList());
        repository.removeRetrospection(retrospection);
        return "redirect:";
    }

}
