package com.girichev.cafe.product.controller;

import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {
    private static final String PRODUCT_ID_REQUEST_HEADER = "X-Product-Id";
    private final ProductsService productsService;

    @Operation(summary = "Get product by id")
    @GetMapping()
    public ProductDto getProduct(@RequestHeader(PRODUCT_ID_REQUEST_HEADER) Integer id) {
        return productsService.getProductById(id);
    }

    @Operation(summary = "Get all product by id")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProductAll() {
        return productsService.getAllProducts(1);
    }
}
