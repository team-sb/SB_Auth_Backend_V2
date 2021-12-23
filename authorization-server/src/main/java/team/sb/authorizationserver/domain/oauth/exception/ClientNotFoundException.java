package team.sb.authorizationserver.domain.oauth.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class ClientNotFoundException extends SBException {

    public static SBException EXCEPTION =
            new ClientNotFoundException();

    private ClientNotFoundException() {
        super(ErrorCode.CLIENT_NOT_FOUND);
    }

}
