package com.girichev.security.controller;

import com.girichev.security.jwt.model.request.JwtRequest;
import com.girichev.security.jwt.model.response.JwtResponse;
import com.girichev.security.service.AuthService;
import com.girichev.utils.PhoneValidate;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private static final String PHONE_REQUEST_PARAM = "phone";
    private final AuthService authService;
    private final PhoneValidate phoneValidate;

    @Operation(summary = "Request an SMS code when registering a phone number")
    @PostMapping("/phone/{phone}")
    public void checkPhoneClient(@NotBlank @PathVariable(PHONE_REQUEST_PARAM) String phone) {
        authService.checkPhoneClient(phoneValidate.validatePhone(phone));
    }

    @Operation(summary = "Request login phone number")
    @PostMapping("/login")
    public JwtResponse createUserToken(@RequestBody JwtRequest request) {
        phoneValidate.validatePhone(request.getPhoneNumber());
        return authService.authenticate(request);
    }

}
