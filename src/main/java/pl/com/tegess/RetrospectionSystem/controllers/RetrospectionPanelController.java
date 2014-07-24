package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

/**
 * Created by Szymek.
 */
@Controller
public class RetrospectionPanelController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("retrospectionPanel")
    public String retrospectionPanel(@RequestParam(value = "id") String id,
                                     Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        model.addAttribute("question", retrospection.getQuestion());
        model.addAttribute("membersNumber", retrospection.getMembersNumber());
        model.addAttribute("id", id);
        model.addAttribute("status", retrospection.getStatus());
        model.addAttribute("tokensList", retrospection.getMembersTokensList());
        return "retrospectionPanel";
    }
}
