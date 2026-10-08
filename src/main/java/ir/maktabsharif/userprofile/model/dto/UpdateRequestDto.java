package ir.maktabsharif.userprofile.model.dto;

import ir.maktabsharif.userprofile.model.UserRole;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Builder
@Data
public class UpdateRequestDto {
    private Long id;

    @Size(min = 5, message = "username must have at least 8 characters!")
    private String username;

    @NotBlank
    private String fullName;

    @Email(message = "enter email in this format: example@gmail.com!")
    private String email;

    @Pattern(regexp = "^(\\+98|0)?9\\d{9}$", message = "invalid phone number!")
    private String phoneNumber;

    private String profileImage;

    private UserRole role;
}
