package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Szymek.
 */
@Controller
public class SignUpController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("/signUp")
    public String createNew(Model model){
        return "signUp";
    }


}
