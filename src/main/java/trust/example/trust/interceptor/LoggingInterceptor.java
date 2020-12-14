package trust.example.trust.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

    /*
    * Get all cookies
    * log sessionId
    * log request path
    */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                            throws Exception{

        String sessionId = null;

           if (request.getCookies() != null){
               for (Cookie cookie: request.getCookies()){
                   if (cookie.getName().equals("JSESSIONID")){
                // the cookie name is JESSIONID for all the cookies in MVC frameworks
                       sessionId = cookie.getValue();
                   }
               }
           }

        System.out.println("Incoming request data log: session:" + sessionId+ " at"+ new Date() + " for "+
                request.getRequestURI());

           return true;
//         true - to make sure the control goes next to the handler
//         false - it won't go any further and return back the response
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception{

        System.out.println("In post handler..");
    }
}
