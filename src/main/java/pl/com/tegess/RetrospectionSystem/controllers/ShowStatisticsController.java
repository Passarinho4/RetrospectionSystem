package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.stickers.Sticker;
import pl.com.tegess.RetrospectionSystem.model.Type;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.DefaultVoteStrategyFactory;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategy;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategyFactory;
import pl.com.tegess.RetrospectionSystem.repositories.RetrospectionRepository;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Szymek.
 */
@Controller
public class ShowStatisticsController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("showStatistics")
    public String showStatistics(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "type") Type type,
                                 Model model){
        RetrospectionRepository repository = applicationContext.getBean(RetrospectionRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        model.addAttribute("type", type.toString().replaceFirst("I", " I").toUpperCase());
        List<Sticker> list = retrospection.getStickersList(type, null);
        VoteStrategyFactory factory = new DefaultVoteStrategyFactory();
        VoteStrategy voteStrategy = factory.getVoteStrategy(retrospection.getVoteStrategyClassName());
        int maxValue = voteStrategy.getMaxVotesValue(retrospection, type);
        list.sort(Comparator.comparingInt(value -> maxValue-value.getVotes()));
        model.addAttribute("stickersList", list);
        model.addAttribute("maxValue", maxValue);
        return "showStatistics";
    }
}
