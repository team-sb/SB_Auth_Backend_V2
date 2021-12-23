package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.user.entity.ProfileImage;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.global.util.S3Util;

@RequiredArgsConstructor
@Component
public class UserProfileFacade {

    private final S3Util s3Util;

    public void addProfileImage(MultipartFile image, User user) {
        String fileName = s3Util.upload(image);
        String fileUrl = s3Util.getFileUrl(fileName);

        user.updateProfileImage(
                new ProfileImage(fileName, fileUrl)
        );
    }

}
