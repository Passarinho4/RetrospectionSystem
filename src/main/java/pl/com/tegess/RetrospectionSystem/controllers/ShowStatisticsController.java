package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.Sticker;
import pl.com.tegess.RetrospectionSystem.repository.RetrospectionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Created by Szymek.
 */
@Controller
public class ShowStatisticsController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("showStatistics")
    public String showStatistics(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "type") String type,
                                 Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        model.addAttribute("type", type.replaceFirst("I", " I").toUpperCase());
        List<Sticker> list = retrospection.getStickersList(type, null);
        Integer membersNumber = retrospection.getMembersNumber();
        list.sort(Comparator.comparingInt(value -> membersNumber-value.getVotes()));
        model.addAttribute("stickersList", list);
        model.addAttribute("membersNumber", membersNumber);
        return "showStatistics";
    }
}
