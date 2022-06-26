package sbnz.skincare.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NewUserDTO {

    @NotBlank
    @Size(min = 3, max = 15)
    @Pattern(message = "Username can contain alphanumeric characters only", regexp = "[a-zA-Z0-9]+")
    private String username;

    @NotBlank
    @Size(min = 8, max = 25)
    private String password;

    @NotBlank
    @Size(min = 3, max = 15)
    @Pattern(message = "Name can contain characters only", regexp = "[a-zA-Z]+")
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    @Pattern(message = "Surname can contain characters only", regexp = "[a-zA-Z]+")
    private String surname;

    @NotBlank
    @Size(min = 5, max = 25)
    @Email(message = "Email should be valid")
    private String email;

    public NewUserDTO() {
    }

    public NewUserDTO(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
