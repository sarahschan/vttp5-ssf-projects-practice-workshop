package sg.edu.nus.iss.vttp5a_practice_workshop.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    
        @NotBlank(message = "Username is mandatory")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9._-]*$", message = "Username can only contain alphabets, letters, '.'', '_', or '-'")
    private String username;

        @NotNull(message = "Age is mandatory")
        // @Min(value = 10, message = "Age must be at least 10 years old")
        @Max(value = 120, message = "Age must not exceed 120")
    private Integer age;
    
    
    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }


    @Override
    public String toString() {
        return username + "," + age;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    
}
