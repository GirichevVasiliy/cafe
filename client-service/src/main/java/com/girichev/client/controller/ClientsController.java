package com.girichev.client.controller;

import com.girichev.client.dto.output.ClientDtoOutput;
import com.girichev.client.service.ClientService;
import com.girichev.utils.PhoneValidate;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ClientsController {
    private final ClientService clientsService;
    private final PhoneValidate phoneValidate;
    private static final String USER_UID_REQUEST_HEADER = "X-User-Uid";

    @Operation(summary = "Update client")
    @PatchMapping()
    public ClientDtoOutput updateClient() {

        return null;
    }

    @Operation(summary = "Get client by uid")
    @GetMapping()
    public ClientDtoOutput getClientById() {
        return null;
    }

    @Operation(summary = "Delete client by uid")
    @DeleteMapping()
    public ClientDtoOutput deleteClientById() {
        return null;
    }
}
