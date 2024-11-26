package me.hyeonjae.stockscope.common.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockErrorCode implements ErrorCode {
    // 주식 관련 에러
    STOCK_NOT_FOUND(404, "S001", "주식을 찾을 수 없습니다"),
    INVALID_STOCK_PRICE(400, "S002", "유효하지 않은 주식 가격입니다"),
    MARKET_CLOSED(400, "S003", "거래시장이 종료되었습니다"),
    PRICE_UPDATE_FAILED(500, "S004", "가격 업데이트에 실패했습니다");

    private final int status;
    private final String code;
    private final String message;
}
