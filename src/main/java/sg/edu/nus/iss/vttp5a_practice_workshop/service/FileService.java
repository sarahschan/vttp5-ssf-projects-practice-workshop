package sg.edu.nus.iss.vttp5a_practice_workshop.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_practice_workshop.constant.Constant;
import sg.edu.nus.iss.vttp5a_practice_workshop.repository.MapRepo;

@Service
public class FileService {
    
    @Autowired
    DateConverter dateConverter;

    @Autowired
    MapRepo mapRepo;

    // Read the file and write to redis
    public void loadFile() throws FileNotFoundException{

        // Read file using input stream
        InputStream inputStream = getClass().getResourceAsStream("/data/todos.txt");
            if (inputStream == null){
                throw new FileNotFoundException();
            }

        // Use JsonReader to read stream
        JsonReader jReader = Json.createReader(inputStream);
        JsonArray jsonTaskArray = jReader.readArray();

        // Process each task from the array
        for (JsonValue taskRaw : jsonTaskArray){
            
            JsonObject taskJson = taskRaw.asJsonObject();
                // Extract details
                String id = taskJson.getString("id");
                String name = taskJson.getString("name");
                String description = taskJson.getString("description");
                String dueDate = taskJson.getString("due_date");
                String priority = taskJson.getString("priority_level");
                String status = taskJson.getString("status");
                    String cleanStatus = cleanStatus(status);
                String createdOn = taskJson.getString("created_at");
                String updatedOn = taskJson.getString("updated_at");

            // Create new JsonObject using extracted values. Perform date conversions
            JsonObject taskJsonSave = Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("dueDate", dateConverter.stringToEpoch(dueDate))
                .add("priority", priority)
                .add("status", cleanStatus)
                .add("createdOn", dateConverter.stringToEpoch(createdOn))
                .add("updatedOn", dateConverter.stringToEpoch(updatedOn))
                .build();

            // Save the new JsonObject to redis
            mapRepo.create(Constant.TASKKEY, id, taskJsonSave.toString());

        }
        
    }


    // helper method to remove the irritating "_" in "in_progress"
    private String cleanStatus(String status){

        if (status.equals("in_progress")){
            String cleanStatus = status.replace("_", " ");
            return cleanStatus;
        }

        return status;
    }

}
