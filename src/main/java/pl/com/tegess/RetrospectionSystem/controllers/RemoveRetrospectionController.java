package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class RemoveRetrospectionController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("removeRetrospection")
    public String removeRetrospection(@RequestParam(value = "id") String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        repository.removeRetrospection(repository.getRetrospectionById(id));
        return "redirect:";
    }
}
