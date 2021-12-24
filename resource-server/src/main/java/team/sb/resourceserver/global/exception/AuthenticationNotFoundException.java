package team.sb.resourceserver.global.exception;

import team.sb.resourceserver.global.error.ErrorCode;
import team.sb.resourceserver.global.error.exception.SBException;

public class AuthenticationNotFoundException extends SBException {

    public static SBException EXCEPTION =
            new AuthenticationNotFoundException();

    private AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }

}
