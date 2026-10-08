package ir.maktabsharif.userprofile.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "user_acount")
public class User extends BaseModel<Long> {
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "profile_image" , columnDefinition = "TEXT")
    private String profileImage;
    @ManyToOne
    private UserRole userRole;
}
