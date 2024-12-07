package sg.edu.nus.iss.vttp5a_practice_workshop.controller.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.User;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list/delete")
public class ListDeleteController {
    
    @Autowired
    TaskService taskService;


    @GetMapping("/{id}")
    public String deleteTask(@PathVariable("id") String id, HttpSession session){
        
        // check if user has logged into session (because after logging out you can still press back and press this button)
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/refused";
        }


        taskService.deleteByID(Constant.TASKKEY, id);
        System.out.println("Deleted task of ID: " + id);
        return "redirect:/list";
    }
}
