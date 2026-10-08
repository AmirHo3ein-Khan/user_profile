package ir.maktabsharif.userprofile.model.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Builder
public class SignupRequestDto {
    @Size(min = 5, message = "username must have at least 8 characters!")
    private String username;

    @Size(min = 4 ,message = "please enter stronger password!")
    private String password;

    @Email(message = "enter email in this format: example@gmail.com!")
    private String email;

    @Pattern(regexp = "^(\\+98|0)?9\\d{9}$", message = "invalid phone number!")
    private String phone;

    @NotBlank
    private String fullName;

    private String profileImage;
}
