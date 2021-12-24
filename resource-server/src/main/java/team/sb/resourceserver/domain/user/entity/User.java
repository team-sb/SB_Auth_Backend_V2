package team.sb.resourceserver.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void updateProfileImage(ProfileImage profile) {
        this.profileImage = profile;
    }

}
