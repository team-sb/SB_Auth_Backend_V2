package team.sb.resourceserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.entity.ProfileImage;
import team.sb.resourceserver.domain.user.entity.User;
import team.sb.resourceserver.domain.user.facade.UserFacade;
import team.sb.resourceserver.global.util.S3Util;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final S3Util s3Util;
    private final UserFacade userFacade;

    @Transactional
    @Override
    public void updateProfileImage(String email, MultipartFile multipartFile) {
        User user = userFacade.getByEmail(email);

        String fileName = s3Util.upload(multipartFile);
        String fileUrl = s3Util.getFileUrl(fileName);

        user.updateProfileImage(
                new ProfileImage(fileName, fileUrl)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoResponse getUserInfo() {
        User user = userFacade.getCurrentUser();

        return new UserInfoResponse(user);
    }

}
