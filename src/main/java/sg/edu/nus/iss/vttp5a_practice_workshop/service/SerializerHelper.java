package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5a_practice_workshop.model.Task;

@Service
public class SerializerHelper {
    
    @Autowired
    DateConverter dateConverter;

    // jsonObject String -> Task POJO
    public Task jsonToPojo(JsonObject jsonTask){
        
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


    // Task POJO -> jsonObject String
    public String pojoToJson(Task task){

        JsonObject taskJson = Json.createObjectBuilder()
                                .add("id", task.getId())
                                .add("name", task.getName())
                                .add("description", task.getDescription())
                                .add("dueDate", dateConverter.localDateToEpoch(task.getDueDate()))
                                .add("priority", task.getPriority())
                                .add("status", task.getStatus())
                                .add("createdOn", dateConverter.localDateToEpoch(task.getCreatedOn()))
                                .add("updatedOn", dateConverter.localDateToEpoch(task.getUpdatedOn()))
                                .build();
        
        return taskJson.toString(); 

    }
}
