package sg.edu.nus.iss.vttp5a_practice_workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
    
    @GetMapping()
    public String redirectToLogin(){
        return "redirect:/login";
    }
}
