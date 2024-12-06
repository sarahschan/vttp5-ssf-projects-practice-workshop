package sg.edu.nus.iss.vttp5a_practice_workshop.controller;

import java.util.ArrayList;
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

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list")
public class ListingController {
    
    @Autowired
    TaskService taskService;

    @GetMapping()
    public String listPage(@RequestParam(name="status", required=false) String status, Model model){

        if (status != null && !status.isEmpty()){
            // if filter status is set, filter the tasks and return the list
            // remember to check if the status is "in+progress"
            if (status.equals("in+progress"))
                status = status.replace("+", " ");
                System.out.println("'in+progress' -> 'in progress'");
                
            List<Task> filteredList = taskService.filterByStatus(status, Constant.TASKKEY);
            model.addAttribute("todoList", filteredList);

        } else {
            // if no filter status, show all tasks
            List<Task> todoList = taskService.getAllTasks(Constant.TASKKEY);
            model.addAttribute("todoList", todoList);
        }

        return "listing";
    }


    @GetMapping("/create")
    public String createTask(Model model){
        
        Task task = new Task();
        model.addAttribute("task", task);

        return "newTask";

    }


    @PostMapping("/create")
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
