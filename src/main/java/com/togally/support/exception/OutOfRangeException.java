package com.togally.support.exception;

/**
 * 超范围异常
 */
public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException(String message) {
        super(message);
    }
}
