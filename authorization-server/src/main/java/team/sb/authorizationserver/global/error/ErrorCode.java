package team.sb.authorizationserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),

    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    INVALID_PASSWORD(401, "Invalid Password"),

    INVALID_AUTH_CODE(401, "Invalid Auth Code"),

    INVALID_CLIENT_SECRET(401, "Invalid Client Secret"),
    CLIENT_NOT_FOUND(404, "Client Not Found"),
    INVALID_OAUTH_CODE(401, "Invalid Oauth Code");

    private final int status;
    private final String message;

}
