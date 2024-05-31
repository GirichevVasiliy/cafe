package com.girichev.excepton;

public class ClientSmsCodeException extends RuntimeException {
    public ClientSmsCodeException(String message) {
        super(message);
    }
}
