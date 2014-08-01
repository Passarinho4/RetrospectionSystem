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
public class StopAddingStickersController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("stopAddingStickers")
    public String stopAddingStickers(@RequestParam(value = "id") String id,
                                     @RequestParam(value = "voteStrategyClassName")String voteStrategyClassName){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        retrospection.setVoteStrategyClassName(voteStrategyClassName);
        retrospection.setStatus(false);
        repository.modifyRetrospection(retrospection);
        return "redirect:retrospectionPanel?id=" + id;
    }
    @RequestMapping("startAddingStickers")
    public String startAddingStickers(@RequestParam(value = "id") String id){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        retrospection.setStatus(true);
        repository.modifyRetrospection(retrospection);
        return "redirect:retrospectionPanel?id=" + id;
    }
}
