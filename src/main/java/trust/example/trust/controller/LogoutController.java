package trust.example.trust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        System.out.println("User is logging out!");
        return"login";
    }
}
