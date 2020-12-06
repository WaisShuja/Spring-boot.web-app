package trust.example.trust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trust.example.trust.beans.Login;
import trust.example.trust.beans.User;
import trust.example.trust.exception.ApplicationException;
import trust.example.trust.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute("login") Login login){   //Note
        System.out.println("User is trying to login...");

        User user  = userRepository.searchByUsername(login.getUsername());
        if (user == null){
            throw new ApplicationException("User not found!");
        }
        return "search";
    }
/*    @ExceptionHandler(ApplicationException.class)
    public String exceptionHandler(){
        System.out.println("In Login E-handler.");
        return "error";

//        Istead let's use GloableExceptionHandler for all controllers..
    }*/
}
