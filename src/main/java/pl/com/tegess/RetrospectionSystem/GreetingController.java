package pl.com.tegess.RetrospectionSystem;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Person;
import pl.com.tegess.RetrospectionSystem.repository.PersonRepository;

import java.net.UnknownHostException;

@Controller
public class GreetingController {

    public Integer integer = 0;

    @Autowired
    public ConfigurableApplicationContext applicationContext;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) throws UnknownHostException {
        integer++;
        PersonRepository personRepository = applicationContext.getBean(PersonRepository.class);
        //personRepository.deleteAll();
        personRepository.insert(new Person(name, 2));
        personRepository.insert(new Person("Johny", 3));
        model.addAttribute("name", name);
        model.addAttribute("count", integer);
        model.addAttribute("db", personRepository.toString());
        return "greeting";

    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        PersonRepository personRepository = applicationContext.getBean(PersonRepository.class);
        personRepository.deleteAll();
        return "deleteAll";
    }
}