package trust.example.trust;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "trust.example.trust")
public class ApplicationConfig extends WebMvcConfigurationSupport {
   
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry){
         registry.addResourceHandler("css/**", "/images/**")
                 .addResourceLocations("classpath:static/css/", "classpath:/static/images/");
     }


     @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
     }

}
