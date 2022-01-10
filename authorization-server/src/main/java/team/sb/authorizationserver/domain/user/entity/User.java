package team.sb.authorizationserver.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "char(60)")
    private String password;

    @Column(nullable = false, columnDefinition = "char(5)")
    private String name;

    @Column(nullable = false, unique = true)
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "char(6)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Embedded
    private ProfileImage profileImage;

    public User(SignupRequest signUpRequest) {
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.name = signUpRequest.getName();
        this.phoneNumber = signUpRequest.getPhoneNumber();
        this.gender = signUpRequest.getGender();
        this.birthDay = signUpRequest.getBirthDay();
    }

    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
