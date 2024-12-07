package sg.edu.nus.iss.vttp5a_practice_workshop.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @GetMapping()
    public String loginPage(Model model){
        
        User user = new User();
        model.addAttribute("user", user);

        return "login";
    }


    @PostMapping()
    public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model){

        // check validity of submitted details
        if (result.hasErrors()){
            // System.out.println("Validation errors: " + result.getAllErrors());
            model.addAttribute("user", user);
            return "login";
        }

        // Store user details in the HTTP session
        session.setAttribute("user", user);

        // Redirect to list page
        return "redirect:/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();

        System.out.println("logout successful");
        
        return "redirect:/login";
    }
}
