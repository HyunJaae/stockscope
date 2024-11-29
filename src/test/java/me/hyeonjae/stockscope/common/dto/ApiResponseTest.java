package me.hyeonjae.stockscope.common.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.hyeonjae.stockscope.common.exception.ErrorResponse;
import me.hyeonjae.stockscope.common.exception.code.CommonErrorCode;

public class ApiResponseTest {
    
    @Test
    void success() {
        // given
        Object data = new Object();

        // when
        ApiResponse<Object> response = ApiResponse.success(data);

        // then
        assertThat(response.getStatus()).isEqualTo("success");
        assertThat(response.getData()).isEqualTo(data);
    }

    @Test
    void error() {
        // given
        ErrorResponse error = ErrorResponse.of(CommonErrorCode.INVALID_INPUT_VALUE);

        // when
        ApiResponse<Object> response = ApiResponse.error(error);

        // then
        assertThat(response.getStatus()).isEqualTo("error");
        assertThat(response.getError()).isEqualTo(error);
    }
}
