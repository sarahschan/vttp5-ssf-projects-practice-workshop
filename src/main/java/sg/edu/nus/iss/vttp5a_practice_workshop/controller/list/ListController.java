package sg.edu.nus.iss.vttp5a_practice_workshop.controller.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.User;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list")
public class ListController {
    
    @Autowired
    TaskService taskService;

    @GetMapping()
    public String listPage(@RequestParam(required=false) String status, HttpSession session, Model model){

        // check if user has logged into session
        User user = (User) session.getAttribute("user");
        System.out.println("Session attribute: " + user);
        if (user == null) {
            return "redirect:/refused";
        }

        // if user has logged in, check for request params (filter by status)
        if (status != null && !status.isEmpty()){
            // if filter status is set, filter the tasks and return the list
            // remember to check if the status is "in+progress"
            if (status.equals("in+progress")){
                status = status.replace("+", " ");
            }

            List<Task> filteredList = taskService.filterByStatus(status, Constant.TASKKEY);
            model.addAttribute("user", user);
            model.addAttribute("todoList", filteredList);

        } else {
            // if no filter status, show all tasks
            List<Task> todoList = taskService.getAllTasks(Constant.TASKKEY);
            model.addAttribute("user", user);
            model.addAttribute("todoList", todoList);
        }

        return "listing";
    }

}
