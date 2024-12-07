package sg.edu.nus.iss.vttp5a_practice_workshop.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sg.edu.nus.iss.vttp5a_practice_workshop.repository.MapRepo;

public class Task {
    
    @Autowired
    MapRepo mapRepo;
    
    private String id;

        @NotBlank(message = "Name is mandatory") 
        @Size(min = 10, max = 50, message = "Name must be between 10 and 50 characters long")
    private String name;

        @NotBlank(message = "Description is mandatory") 
        @Size(max = 225, message = "Description must be between 1 and 225 characters long")
    private String description;

        @NotNull(message = "Due date is mandatory")
        @FutureOrPresent(message = "Due date must be today or a future date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

        @NotNull(message = "Description is mandatory")
    private String priority;

        @NotNull(message = "Status is mandatory")
    private String status;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;
    
    private LocalDate updatedOn;
    
    
    public Task() {
    }

    public Task(String id, String name, String description, LocalDate dueDate, String priority, String status, LocalDate createdOn, LocalDate updatedOn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Task(String name, String description, LocalDate dueDate, String priority, String status) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdOn = LocalDate.now();
        this.updatedOn = LocalDate.now();
    }


    public Task(String id, String name, String description, LocalDate dueDate, String priority, String status, LocalDate createdOn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = LocalDate.now();
    }

    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + dueDate + "," + priority + "," + status + "," + createdOn + "," + updatedOn;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

}
