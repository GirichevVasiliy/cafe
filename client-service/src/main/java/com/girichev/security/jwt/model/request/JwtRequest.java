package com.girichev.security.jwt.model.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class JwtRequest {
    @Pattern(regexp = "^7[0-9]{10}$", message = "Телефонный номер должен соответствовать формату 7XXXXXXXXXX")
    private String phoneNumber;
    private String password;
}
