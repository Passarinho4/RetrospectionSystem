package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.DefaultGenerator;
import pl.com.tegess.RetrospectionSystem.model.Generator;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class CreateNewController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("/createNew/")
    public String createNew(Model model){
        return "createNew";
    }

    @RequestMapping("/createNew/signUp")
    public String signUp(@RequestParam(value = "author") String author,
                         @RequestParam(value = "question") String question,
                         @RequestParam(value = "membersNumber") Integer membersNumber,
                         Model model) {
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Generator generator = new DefaultGenerator(repository);
        Retrospection retrospection = new Retrospection(generator.getId(), author, question, generator.getTokens(membersNumber));
        repository.insertRetrospection(retrospection);
        model.addAttribute("question", question);
        model.addAttribute("membersNumber", membersNumber);
        return "signUp";
    }
}
