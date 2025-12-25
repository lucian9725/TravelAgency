package sda.academy.travelagency.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("TravelAgency", "Welcome to the TravelAgency Home Page");
        return "home";
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

}

