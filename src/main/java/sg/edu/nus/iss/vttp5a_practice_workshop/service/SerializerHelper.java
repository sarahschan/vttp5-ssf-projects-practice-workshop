package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;

@Service
public class SerializerHelper {
    
    @Autowired
    DateConverter dateConverter;

    // jsonTask -> Task POJO
    public Task jsonToTask(JsonObject jsonTask){
        
        Task task = new Task();
            task.setId(jsonTask.getString("id"));
            task.setName(jsonTask.getString("name"));
            task.setDescription(jsonTask.getString("description"));
            task.setDueDate(dateConverter.epochToLocalDate(jsonTask.getJsonNumber("dueDate").longValue()));
            task.setPriority(jsonTask.getString("priority"));
            task.setStatus(jsonTask.getString("status"));
            task.setCreatedOn(dateConverter.epochToLocalDate(jsonTask.getJsonNumber("createdOn").longValue()));
            task.setUpdatedOn(dateConverter.epochToLocalDate(jsonTask.getJsonNumber("updatedOn").longValue()));

        return task;

    }


    // 
}
