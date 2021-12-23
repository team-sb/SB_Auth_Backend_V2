package team.sb.authorizationserver.domain.oauth.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class InvalidClientSecret extends SBException {

    public static SBException EXCEPTION =
            new InvalidClientSecret();

    private InvalidClientSecret() {
        super(ErrorCode.INVALID_CLIENT_SECRET);
    }

}
