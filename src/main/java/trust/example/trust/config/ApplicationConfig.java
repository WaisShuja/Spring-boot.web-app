package trust.example.trust.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import trust.example.trust.converter.StringToEnumConverter;
import trust.example.trust.interceptor.LoggingInterceptor;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "trust.example.trust")
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "/images/**")
                .addResourceLocations("classpath:static/css/", "classpath:/static/images/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(3);
        return viewResolver;
    }


    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

/*
      Asynchronous Support
    1: Async processing flag on Dispatcher Servlet is set auto.. to true
    2: Add Async processing configuration in WebConfig if needed (a method to override)
    3: Adding a Callable or DeferredResult on a target Controller...
*/


    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor mvcTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Trust-thread-");
        return threadPoolTaskExecutor;
    }

//    ViewResolver through XML
    @Bean
    public XmlViewResolver xmlVresolver(){
        XmlViewResolver viewResolver = new XmlViewResolver();
        viewResolver.setLocation(new ClassPathResource("xmlViews.xml"));
        viewResolver.setOrder(1);
        return viewResolver;
    }


//    ViewResolver through .properties

    @Bean
    public ResourceBundleViewResolver bundleViewResolver(){
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("viewRBVR"); // All views are defined in viewRBVR.properties file
        viewResolver.setOrder(2);
        return viewResolver;
    }


//    To make Spring aware of our custom Interceptor, we add the interceptorClass
    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
//        we call the interceptor for every pattern in the application


//        Configuring ThemeChangeInterceptor to tap a change in theme value for every request
        registry.addInterceptor(new ThemeChangeInterceptor());


//        Adding LocaleChangeInterceptor to intercept language parameter passed as query parameter
        registry.addInterceptor(new LocaleChangeInterceptor());

//        we can also customize the para's name
    }

    @Bean
    public ThemeResolver themeResolver(){
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setCookieName("Theme");
        cookieThemeResolver.setDefaultThemeName("defaultTheme");
        return cookieThemeResolver;
    }


//    		-- Locale Resolution with Interceptors --

        @Bean
        public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.UK);
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;
        }

}