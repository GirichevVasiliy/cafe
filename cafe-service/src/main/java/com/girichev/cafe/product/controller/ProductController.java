package com.girichev.cafe.product.controller;

import com.girichev.cafe.dto.ProductDto;
import com.girichev.cafe.product.service.ProductServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {
    private final ProductServiceClient productServiceClient;
    @Operation(summary = "Get product by id")
    @GetMapping()
    public ProductDto getProduct(@RequestParam("id") Integer id){
        return productServiceClient.getProduct(id);
    }
}
