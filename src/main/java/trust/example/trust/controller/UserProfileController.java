package trust.example.trust.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import trust.example.trust.beans.Login;

@Controller
public class UserProfileController {

    @PostMapping("/userProfile")
    public String getUserProfile(@SessionAttribute("login")Login login, Model model){
        System.out.println("User is in User-Profile-Controller!");
        System.out.println("User name from session: "+ login.getUsername());
        model.addAttribute("logUsername", login.getUsername());
        return "profile";
    }
}
