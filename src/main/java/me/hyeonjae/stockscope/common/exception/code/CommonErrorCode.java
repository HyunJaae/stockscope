package me.hyeonjae.stockscope.common.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    
    // 서버 에러
    INTERNAL_SERVER_ERROR(500, "C001", "서버 에러가 발생했습니다"),
    INVALID_INPUT_VALUE(400, "C002", "잘못된 입력값입니다"),
    UNAUTHORIZED(401, "C003", "인증되지 않은 접근입니다"),
    FORBIDDEN(403, "C004", "접근 권한이 없습니다"),
    
    // 외부 시스템 에러
    EXTERNAL_API_ERROR(500, "C005", "외부 API 호출 중 에러가 발생했습니다");

    private final int status;
    private final String code;
    private final String message;
}
