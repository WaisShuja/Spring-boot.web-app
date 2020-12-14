package trust.example.trust.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import javax.persistence.NonUniqueResultException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class, AsyncRequestTimeoutException.class,
            IllegalArgumentException.class, NonUniqueResultException.class})
    public String globalHandler(){
        System.out.println("In Global E-handler!");
        return "error";
    }
}
