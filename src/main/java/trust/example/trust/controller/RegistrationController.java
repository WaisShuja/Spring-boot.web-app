package trust.example.trust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trust.example.trust.beans.User;
import trust.example.trust.repository.UserRepository;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true));
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newuser")User user, BindingResult result, Model model){
         if(result.hasErrors()){
             System.out.println("Form fields error! User is refilling the form... ");
             return "register";
         }
        System.out.println("User is being registered!");
        userRepository.save(user);
        model.addAttribute("userRegistered", "User registered successfully!");
        return "login";
    }


}
