package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.DefaultIdGenerator;
import pl.com.tegess.RetrospectionSystem.model.IdGenerator;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepositoryMongoDB;

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
        IdGenerator generator = new DefaultIdGenerator();
        Retrospection retrospection = new Retrospection(generator.getId(), author, question, membersNumber);
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        repository.insertRetrospection(retrospection);
        model.addAttribute("question", question);
        model.addAttribute("membersNumber", membersNumber);
        return "signUp";
    }
}
