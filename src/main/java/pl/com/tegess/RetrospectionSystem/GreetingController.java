package pl.com.tegess.RetrospectionSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import pl.com.tegess.RetrospectionSystem.configuration.WebAppConfiguration;
import pl.com.tegess.RetrospectionSystem.model.Person;
import pl.com.tegess.RetrospectionSystem.repository.PersonRepository;

@Controller
public class GreetingController {
    public Integer integer = 0;
    @Autowired
    public ConfigurableApplicationContext applicationContext;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        integer++;

        PersonRepository personRepository = applicationContext.getBean(PersonRepository.class);
        personRepository.insert(new Person("Szymek", 2));
        personRepository.insert(new Person("JAJA", 3));
        model.addAttribute("name", name);
        model.addAttribute("count", integer);
        model.addAttribute("db", personRepository.toString());
        return "greeting";

    }

}