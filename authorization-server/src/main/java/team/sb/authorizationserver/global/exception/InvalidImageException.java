package team.sb.authorizationserver.global.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidImageException extends SBException {

    public static SBException EXCEPTION =
            new InvalidImageException();

    private InvalidImageException() {
        super(ErrorCode.INVALID_IMAGE);
    }

}
