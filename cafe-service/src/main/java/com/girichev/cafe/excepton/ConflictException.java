package com.girichev.cafe.excepton;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
