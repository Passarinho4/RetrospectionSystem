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

import java.util.List;

/**
 * Created by Szymek.
 */
@Controller
public class CreateRetrospectionController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

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

}
