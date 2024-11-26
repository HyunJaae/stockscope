package me.hyeonjae.stockscope.common.exception;

import lombok.Getter;
import me.hyeonjae.stockscope.common.exception.code.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    
    private final ErrorCode errorCode;
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
