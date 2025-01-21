package ir.maktabsharif.userprofile.model.dto;
import ir.maktabsharif.userprofile.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class LoginResponseDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;

}
