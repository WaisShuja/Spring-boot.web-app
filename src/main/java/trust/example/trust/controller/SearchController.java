package trust.example.trust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import trust.example.trust.beans.Product;
import trust.example.trust.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    ProductRepository productRepository;

    //    Need to autowire AsyncTaskExecutor
    @Autowired
    AsyncTaskExecutor executor;


    @GetMapping("/search")
    public DeferredResult<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request) {
        DeferredResult deferredResult = new DeferredResult();
        System.out.println("user is in search controller!");
        System.out.println("Search criteria: " + search);
//     AsyncSupport flag enabling
        System.out.println("Async Supported enabled? > " + request.isAsyncSupported());
//     Thread From Serlvet Container
        System.out.println("Thread from Servlet container: " + Thread.currentThread().getName());

        executor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Thread from Spring MVC task-executor:" + Thread.currentThread().getName());

            List<Product> products = new ArrayList<>();
            products = productRepository.searchByName(search);
            model.addAttribute("products", products);
            deferredResult.setResult("search");

        });
        return deferredResult;
/*        return (()->{
//
//        });
//        List<Product> products = new ArrayList<>();
//        products = productRepository.searchByName(search);
//        model.addAttribute("products",products);
//        return "search";
    }*/

    }

}