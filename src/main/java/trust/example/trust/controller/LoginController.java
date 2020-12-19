package trust.example.trust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import trust.example.trust.beans.Login;
import trust.example.trust.beans.User;
import trust.example.trust.exception.ApplicationException;
import trust.example.trust.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;


//   3: Return a Callable<String> / DefferedResult<String>
    @PostMapping("/userLogin")
    public Callable <String> userLogin(@ModelAttribute("login") Login login, HttpServletRequest request){   //Note

//        Full session management can be done here too, using HttpSession.

        System.out.println("User is trying to login...");
//    To make sure the Async flag is enabled >
        System.out.println("Async Supported in Trust Application: "+ request.isAsyncSupported());

//     Getting the Servlet container Thread's name..
        System.out.println("Thread from Servlet container:"+ Thread.currentThread().getName());

        return ()->{
            Thread.sleep(3000);
            System.out.println("Thread from Spring mvc container:"+ Thread.currentThread());
            User user  = userRepository.searchByUsername(login.getUsername());
            if (user == null){
                throw new ApplicationException("User not found!");
            }
            return "forward:/userProfile";
        };

    }
/*    @ExceptionHandler(ApplicationException.class)
    public String exceptionHandler(){
        System.out.println("In Login E-handler.");
        return "error";

//        Istead let's use GloableExceptionHandler for all controllers..
    }*/
}
