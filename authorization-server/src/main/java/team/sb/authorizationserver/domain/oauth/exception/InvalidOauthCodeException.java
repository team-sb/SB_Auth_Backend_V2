package team.sb.authorizationserver.domain.oauth.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidOauthCodeException extends SBException {

    public static SBException EXCEPTION =
            new InvalidOauthCodeException();

    private InvalidOauthCodeException() {
        super(ErrorCode.INVALID_OAUTH_CODE);
    }

}
