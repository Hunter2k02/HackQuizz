package HackerQuizz.dto;

import HackerQuizz.validation.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@PasswordMatch
public class UserRegisterDTO {
    @NotBlank(message = "Username is required")
            @Size(min=3, max=20, message = "Username must be between 3 and 20 characters")
    String username;
    @NotBlank(message = "Email is required")
            @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "Password is required")
            @Size(min=6, message = "Password must be at least 6 characters")
    String password;
    @NotBlank(message = "Confirm Password is required")
    String confirmPassword;
    public UserRegisterDTO(){}
    public UserRegisterDTO(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public @NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public @NotBlank(message = "Confirm Password is required") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setUsername(@NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String username) {
        this.username = username;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }

    public void setConfirmPassword(@NotBlank(message = "Confirm Password is required") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
