package nl.intergamma.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    private final String appName;

    @Autowired
    public HomeController(@Value("${spring.application.name}") String appName) {
        this.appName = appName;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("appName", appName);
        return "products";
    }

    @GetMapping("/addStore")
    public String addStore(Model model) {
        model.addAttribute("appName", appName);
        return "addStore";
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("appName", appName);
        return "addProduct";
    }
}
