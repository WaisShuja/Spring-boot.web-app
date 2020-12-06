package trust.example.trust.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import trust.example.trust.beans.Login;
import trust.example.trust.beans.User;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DefaultModelAttributeController {

    @ModelAttribute("genderItems")
    public List<String> genderItems(){
        return Arrays.asList(new String []{"Male","Female","Other"});
    }

    @ModelAttribute("newuser")
    public User getDefaultUser(){
        return new User();
    }

    @ModelAttribute("login")
    public Login defaultLogin(){
        return new Login();
    }

}
