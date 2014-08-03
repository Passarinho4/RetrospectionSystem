package pl.com.tegess.RetrospectionSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.com.tegess.RetrospectionSystem.model.Retrospection;
import pl.com.tegess.RetrospectionSystem.model.users.Member;
import pl.com.tegess.RetrospectionSystem.model.users.User;
import pl.com.tegess.RetrospectionSystem.parsers.CSVMailsParser;
import pl.com.tegess.RetrospectionSystem.parsers.DefaultMailsParserFactory;
import pl.com.tegess.RetrospectionSystem.parsers.MailsParser;
import pl.com.tegess.RetrospectionSystem.parsers.MailsParserFactory;
import pl.com.tegess.RetrospectionSystem.repositories.RetrospectionRepository;
import pl.com.tegess.RetrospectionSystem.repositories.UserRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        Retrospection retrospection = repository.getRetrospectionById(id);
        model.addAttribute("question", retrospection.getQuestion());
        model.addAttribute("membersNumber", retrospection.getMembersNumber());
        model.addAttribute("id", id);
        model.addAttribute("status", retrospection.getStatus());
        model.addAttribute("usersList", userRepository.getUsersByTokens(retrospection.getMembersTokensList()));
        return "retrospectionPanel";
    }

    @RequestMapping("addUserMail")
    public String addUserMail(@RequestParam(value = "token") String token,
                              @RequestParam(value = "mail") String mail,
                              @RequestParam(value = "id") String id){
        UserRepository repository = applicationContext.getBean(UserRepository.class);
        User user = repository.getUserByToken(token);
        user.setMail(mail);
        repository.modifyUser(user);
        return "redirect:retrospectionPanel?id="+id;
    }

    @RequestMapping("addUsersMails")
    public String addUsersMails(@RequestParam(value = "file")MultipartFile file,
                                @RequestParam(value = "id") String id){
        //upload file
        File f = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                f = new File(file.getName() + "-uploaded");
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(f));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "404";
        }
        RetrospectionRepository retrospectionRepository = applicationContext.getBean(RetrospectionRepository.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        MailsParserFactory factory = new DefaultMailsParserFactory();
        MailsParser parser = factory.createMailsParser(file.getContentType());
        List<String> mails = parser.getMails(f);
        List<String> tokens = retrospectionRepository.getRetrospectionById(id).getMembersTokensList();
        Iterator<String> mailsIterator = mails.iterator();
        User user;
        for (String token : tokens) {
            if (mailsIterator.hasNext()) {
                user = userRepository.getUserByToken(token);
                user.setMail(mailsIterator.next());
                userRepository.modifyUser(user);
            }
        }
        if (f != null) {
            f.delete();
        }
        return "redirect:retrospectionPanel?id="+id;
    }

}
