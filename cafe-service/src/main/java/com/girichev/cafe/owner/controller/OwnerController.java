package com.girichev.cafe.owner.controller;

import com.girichev.cafe.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping()
    public String getOwner() {
    return ownerService.getOwner(101L);
    }
}

