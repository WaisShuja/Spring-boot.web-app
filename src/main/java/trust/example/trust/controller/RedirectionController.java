package trust.example.trust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

    @GetMapping("/linkedIn")
    public String redirectToLinkedIn(){
        System.out.println("Using is redirected to LinkedIn.com");
        return "redirect:https://www.linkedin.com/in/wais-shuja-14656a154/";
    }
}
