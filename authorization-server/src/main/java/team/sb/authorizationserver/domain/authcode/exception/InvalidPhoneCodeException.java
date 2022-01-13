package team.sb.authorizationserver.domain.authcode.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidPhoneCodeException extends SBException {

    public static SBException EXCEPTION =
            new InvalidPhoneCodeException();

    private InvalidPhoneCodeException() {
        super(ErrorCode.INVALID_PHONE_CODE);
    }

}