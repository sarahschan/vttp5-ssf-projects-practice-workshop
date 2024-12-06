package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;
import sg.edu.nus.iss.vttp5a_practice_workshop.repository.MapRepo;

@Service
public class TaskService {
    
    @Autowired
    MapRepo mapRepo;

    @Autowired
    SerializerHelper serializerHelper;


    // Get all tasks from redis
    public List<Task> getAllTasks(String redisKey){
        
        List<Task> todoList = new ArrayList<>();

        Map<Object, Object> entries = mapRepo.getEntries(redisKey);
        
        for (Map.Entry<Object, Object> entry : entries.entrySet()){
            
            JsonReader jReader = Json.createReader(new StringReader(entry.getValue().toString()));
            JsonObject jsonToDo = jReader.readObject();

            Task task = serializerHelper.jsonToPojo(jsonToDo);

            todoList.add(task);

        }

        return todoList;
    }


    // Filter tasks by status
    public List<Task> filterByStatus(String status, String redisKey) {
        
        // Get all the tasks
        List<Task> allTasks = getAllTasks(redisKey);

        // filter by status
        List<Task> filteredTasks = allTasks.stream()
                                    .filter(task -> task.getStatus().equalsIgnoreCase(status))
                                    .collect(Collectors.toList());
    
        return filteredTasks;

    }


    public void addTask(String redisKey, String taskID, Task taskPOJO) {
        
        // Serialize task POJO -> JsonObject String
        String taskJsonString = serializerHelper.pojoToJson(taskPOJO);

        // Save to redis
        mapRepo.create(redisKey, taskID, taskJsonString);
    }
}
