package trust.example.trust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

        @GetMapping("/home")
        public String goToHome(){
        System.out.println("In home controller!");
        return"index";
    }

        @GetMapping("/goToSearch")
        public String goToSearch(){
            System.out.println("Going to Search page..");
            return "search";
        }
}
