package team.sb.resourceserver.domain.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.sb.resourceserver.domain.user.entity.User;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private final String name;

    private final String email;

    private final String phoneNumber;

    private final String profileUrl;

    public UserInfoResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profileUrl = user.getProfileImage().getProfileUrl();
    }

}
