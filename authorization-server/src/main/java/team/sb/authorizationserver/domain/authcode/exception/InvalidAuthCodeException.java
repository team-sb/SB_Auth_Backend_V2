package team.sb.authorizationserver.domain.authcode.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidAuthCodeException extends SBException {

    public static SBException EXCEPTION =
            new InvalidAuthCodeException();

    private InvalidAuthCodeException() {
        super(ErrorCode.INVALID_AUTH_CODE);
    }

}
