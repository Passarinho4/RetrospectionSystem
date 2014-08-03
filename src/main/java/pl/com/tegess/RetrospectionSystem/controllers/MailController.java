package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.tegess.RetrospectionSystem.model.Mail;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.DefaultVoteStrategyFactory;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategy;
import pl.com.tegess.RetrospectionSystem.model.voteStrategy.VoteStrategyFactory;
import pl.com.tegess.RetrospectionSystem.repositories.RetrospectionRepository;
import pl.com.tegess.RetrospectionSystem.repositories.UserRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Szymek.
 */
@Controller
public class MailController {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @RequestMapping("sendTokens")
    public String sendTokens(@RequestParam(value = "id")String id){

        RetrospectionRepository retrospectionRepository = applicationContext.getBean(RetrospectionRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Mail mail = applicationContext.getBean(Mail.class);
        String mailContent = "Hi, here is your link to retrospection:" +
                " http://localhost:8080/RetrospectionSystem/retrospection?token=";
        List<String> tokens = retrospectionRepository.getRetrospectionById(id).getMembersTokensList();
        User user;
        for(String token: tokens){
            user = userRepository.getUserByToken(token);
            if(user.getMail()!=null && !user.getMail().equals("")){
                mail.sendMail(user.getMail(), "Retrospection System", mailContent+token);
            }
        }
        return "redirect:retrospectionPanel?id="+id;
    }

    @RequestMapping("sendVoteStrategy")
    public String sendVoteStrategy(@RequestParam(value = "id")String id){

        RetrospectionRepository retrospectionRepository = applicationContext.getBean(RetrospectionRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Mail mail = applicationContext.getBean(Mail.class);
        VoteStrategyFactory factory = new DefaultVoteStrategyFactory();
        Retrospection retrospection = retrospectionRepository.getRetrospectionById(id);
        VoteStrategy voteStrategy = factory.getVoteStrategy(retrospection.getVoteStrategyClassName());
        String mailContent = "Hi, now you can vote in Retrospection System. Here is short description of voting system: "
                + voteStrategy.getDescription()  +
                "\n Here is link, where you can vote: http://localhost:8080/RetrospectionSystem/retrospection?token=";
        List<String> tokens = retrospectionRepository.getRetrospectionById(id).getMembersTokensList();
        User user;
        for(String token: tokens){
            user = userRepository.getUserByToken(token);
            if(user.getMail()!=null && !user.getMail().equals("")){
                mail.sendMail(user.getMail(), "Retrospection System", mailContent+token);
            }
        }

        return "redirect:retrospectionPanel?id="+id;
    }
}
