package trust.example.trust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trust.example.trust.beans.User;
import trust.example.trust.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("newuser")User user, Model model){
        System.out.println("User is being registered!");
        userRepository.save(user);
        model.addAttribute("userRegistered", "User registered successfully!");
        return "login";
    }


}
