package trust.example.trust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import trust.example.trust.beans.User;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

        @GetMapping("/home")
        public String goToHome(){
        System.out.println("In home controller!");
        return"index";
    }

        @GetMapping("/goToSearch")
        public String goToSearch(){
            System.out.println("User is going to Search page..");
            return "search";
        }

        @GetMapping("/goToLogin")
        public String goToLogin(){
            System.out.println("User is going to Login page...");
            return "login";
        }

        @GetMapping("/goToRegistration")
         public String goToRegistration(){
            System.out.println("User is going to Registration page...");
            return "register";
        }

        @ModelAttribute("genderItems")
        public List<String> genderItems(){
            return Arrays.asList(new String []{"Male","Female","Other"});
        }

        @ModelAttribute("newuser")
        public User getDefaultUser(){
            return new User();
        }
}
