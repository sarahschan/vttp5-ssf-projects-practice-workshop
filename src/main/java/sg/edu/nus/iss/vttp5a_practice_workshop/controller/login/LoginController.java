package sg.edu.nus.iss.vttp5a_practice_workshop.controller.login;

import javax.naming.Binding;

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

    // Post mapping before redirect to underage page (remember to uncomment validator on User model)
    // @PostMapping()
    // public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model){

    //     // check validity of submitted details
    //     if (result.hasErrors()){
    //         // System.out.println("Validation errors: " + result.getAllErrors());
    //         model.addAttribute("user", user);
    //         return "login";
    //     }

    //     // Store user details in the HTTP session
    //     session.setAttribute("user", user);

    //     // Redirect to list page
    //     return "redirect:/list";
    // }


    // Post mapping for redirect to underage page
    @PostMapping()
    public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session){

        // check validity of submitted details
        if (result.hasErrors()){
            model.addAttribute("user", user);
            return "login";
        }

        // if user is under 10, redirect to underage.html
        if (user.getAge() < 10) {
            return "underage";
        }

        // if all is ok, store user details in HTTP session and redirect to list page
        session.setAttribute("user", user);
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
