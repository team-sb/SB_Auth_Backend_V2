package team.sb.resourceserver.global.exception;

import team.sb.resourceserver.global.error.ErrorCode;
import team.sb.resourceserver.global.error.exception.SBException;

public class ExpiredTokenException extends SBException {

    public static SBException EXCEPTION =
            new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}
