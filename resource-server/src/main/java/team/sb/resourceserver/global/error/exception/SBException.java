package team.sb.resourceserver.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.sb.resourceserver.global.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public class SBException extends RuntimeException {

    private final ErrorCode errorCode;

}
