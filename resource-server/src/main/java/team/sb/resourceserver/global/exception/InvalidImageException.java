package team.sb.resourceserver.global.exception;

import team.sb.resourceserver.global.error.ErrorCode;
import team.sb.resourceserver.global.error.exception.SBException;

public class InvalidImageException extends SBException {

    public static SBException EXCEPTION =
            new InvalidImageException();

    private InvalidImageException() {
        super(ErrorCode.INVALID_IMAGE);
    }

}
