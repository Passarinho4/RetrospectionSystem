package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymek.
 */
@Controller
public class ShowAllDataController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("/showAllData/")
    public String showAllData(Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        model.addAttribute("retrospections", repository.getAllRetrospections());
        return "showAllData";
    }
}
