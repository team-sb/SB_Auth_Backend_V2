package team.sb.resourceserver.domain.user.exception;

import team.sb.resourceserver.global.error.ErrorCode;
import team.sb.resourceserver.global.error.exception.SBException;

public class UserNotFoundException extends SBException {

    public static SBException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
