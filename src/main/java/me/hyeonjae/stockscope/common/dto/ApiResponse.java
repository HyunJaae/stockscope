package me.hyeonjae.stockscope.common.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hyeonjae.stockscope.common.exception.ErrorResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    
    private String status;
    private T data;
    private ErrorResponse error;
    private LocalDateTime timestamp;
    
    private ApiResponse(String status, T data, ErrorResponse error) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null);
    }
    
    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return new ApiResponse<>(ERROR_STATUS, null, error);
    }
}
