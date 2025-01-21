package ir.maktabsharif.userprofile.model.dto;

import ir.maktabsharif.userprofile.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoggedInDto {
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private UserRole role;
}
