package me.hyeonjae.stockscope.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import me.hyeonjae.stockscope.common.exception.code.CommonErrorCode;
import me.hyeonjae.stockscope.common.exception.code.ErrorCode;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("Business Exception: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
            ErrorResponse.of(errorCode),
            HttpStatus.valueOf(errorCode.getStatus())
        );
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Validation Exception: {}", e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INVALID_INPUT_VALUE;
        return new ResponseEntity<>(
            ErrorResponse.of(errorCode, e.getBindingResult()),
            HttpStatus.valueOf(errorCode.getStatus())
        );
    }
}
