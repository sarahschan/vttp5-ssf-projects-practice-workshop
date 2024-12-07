package sg.edu.nus.iss.vttp5a_practice_workshop.controller.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.User;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list/edit")
public class ListEditController {
    
    @Autowired
    TaskService taskService;


    @GetMapping("/{id}")
    public String updateTask(@PathVariable("id") String id, Model model, HttpSession session){
        
        // check if user has logged into session (because after logging out you can still press back and press this button)
        User user = (User) session.getAttribute("user");
        System.out.println("Session attribute: " + user);
        if (user == null) {
            return "redirect:/refused";
        }

        
        Task task = taskService.findByID(Constant.TASKKEY, id);
        System.out.println("Editing task: " + task);
        model.addAttribute("task", task);

        return "editTask";
    }


    @PostMapping()
    public String handleUpdateTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("task", task);
            return "editTask";
        }

        // Use the appropriate constructor - Remember that the task ID and created date SHOULD NOT CHANGE
        System.out.println("Recieved data: " + task);
        Task editedTask = new Task(task.getId(),
                                   task.getName(),
                                   task.getDescription(),
                                   task.getDueDate(),
                                   task.getPriority(),
                                   task.getStatus(),
                                   task.getCreatedOn());
        System.out.println("Updating to: " + editedTask);
        taskService.addTask(Constant.TASKKEY, editedTask.getId(), editedTask);
        return "redirect:/list";
    }
}
