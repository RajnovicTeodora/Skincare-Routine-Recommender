package sbnz.skincare.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class JwtAuthenticationRequest {

    @NotBlank
    @Size(min=3, max=15)
    @Pattern(message = "Username can contain alphanumeric characters only", regexp = "[a-zA-Z0-9 ]+")
    private String username;

    @NotBlank
    @Size(min=2, max=25)
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
