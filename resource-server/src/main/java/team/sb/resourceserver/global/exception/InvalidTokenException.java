package team.sb.resourceserver.global.exception;

import team.sb.resourceserver.global.error.ErrorCode;
import team.sb.resourceserver.global.error.exception.SBException;

public class InvalidTokenException extends SBException {

    public static SBException EXCEPTION =
            new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}
