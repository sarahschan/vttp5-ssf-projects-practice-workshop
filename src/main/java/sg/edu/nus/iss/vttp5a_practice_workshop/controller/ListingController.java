package sg.edu.nus.iss.vttp5a_practice_workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.service.TaskService;

@Controller
@RequestMapping("/list")
public class ListingController {
    
    @Autowired
    TaskService taskService;

    @GetMapping()
    public String listPage(Model model){

        List<Task> todoList = taskService.getAllTasks(Constant.TASKKEY);
        model.addAttribute("todoList", todoList);
        
        return "listing";
    }
}
