package team.sb.authorizationserver.domain.authcode.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidEmailCodeException extends SBException {

    public static SBException EXCEPTION =
            new InvalidEmailCodeException();

    private InvalidEmailCodeException() {
        super(ErrorCode.INVALID_EMAIL_CODE);
    }

}
