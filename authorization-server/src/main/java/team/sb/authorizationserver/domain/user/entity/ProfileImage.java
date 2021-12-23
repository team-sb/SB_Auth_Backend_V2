package team.sb.authorizationserver.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfileImage {

    private String profilePath;

    private String profileUrl;

}
