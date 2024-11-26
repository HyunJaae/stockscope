package me.hyeonjae.stockscope.common.exception.code;

public interface ErrorCode {
    String name();
    int getStatus();
    String getCode();
    String getMessage();
}
