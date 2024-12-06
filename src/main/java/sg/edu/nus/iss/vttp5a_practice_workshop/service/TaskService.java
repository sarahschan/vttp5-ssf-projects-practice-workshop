package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

            Task task = serializerHelper.jsonToTask(jsonToDo);

            todoList.add(task);
            
        }

        return todoList;
    }
}
