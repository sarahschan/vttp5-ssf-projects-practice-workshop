package sg.edu.nus.iss.vttp5a_practice_workshop.controller.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.User;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list/create")
public class ListCreateController {
    
    @Autowired
    TaskService taskService;


    @GetMapping()
    public String createTask(Model model, HttpSession session){
        
        // check if user has logged into session (because after logging out you can still press back and press this button)
        User user = (User) session.getAttribute("user");
        System.out.println("Session attribute: " + user);
        if (user == null) {
            return "redirect:/refused";
        }

        Task task = new Task();
        model.addAttribute("task", task);

        return "newTask";

    }


    @PostMapping()
    public String handleCreateTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model){
        
        if (result.hasErrors()){
            model.addAttribute("task", task);
            return "newTask";
        }

        Task newTask = new Task(task.getName(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus());
        taskService.addTask(Constant.TASKKEY, newTask.getId(), newTask);
        return "redirect:/list";
    }

}
