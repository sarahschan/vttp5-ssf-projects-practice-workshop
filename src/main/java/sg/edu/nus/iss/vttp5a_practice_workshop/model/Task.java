package sg.edu.nus.iss.vttp5a_practice_workshop.model;

import java.time.LocalDate;

public class Task {
    
    private String id;
    private String name;
    private String desc;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    
    
    public Task() {
    }

    public Task(String id, String name, String desc, LocalDate dueDate, String priority, String status,
            LocalDate createdOn, LocalDate updatedOn) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    
    @Override
    public String toString() {
        return id + "," + name + "," + desc + "," + dueDate + "," + priority + "," + status + "," + createdOn + "," + updatedOn;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
