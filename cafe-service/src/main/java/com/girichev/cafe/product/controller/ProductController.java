package com.girichev.cafe.product.controller;

import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    private static final String ORDER_TYPE_ID_REQUEST_HEADER = "X-Order-Type-Id";
    private static final String USER_ID_REQUEST_HEADER = "X-User-Id"; // подумать
    private final ProductsService productsService;
    @Operation(summary = "Add new product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@Valid @RequestBody ProductDto productDto){
        return productsService.addProduct(productDto);
    }
    @Operation(summary = "Update product")
    @PatchMapping
    public ProductDto updateProduct(@Valid @RequestBody ProductDto productDto){
        return productsService.updateProduct(productDto);
    }
    @Operation(summary = "Get product by id")
    @GetMapping
    public ProductDto getProductById(@RequestHeader(PRODUCT_ID_REQUEST_HEADER) Integer id) {
        return productsService.getProductById(id);
    }
    @Operation(summary = "Delete product by id")
    @DeleteMapping
    public int deleteProductById(@RequestHeader(PRODUCT_ID_REQUEST_HEADER) Integer id) {
        return productsService.deleteProduct(id);
    }

    @Operation(summary = "Get all product by id")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProductAll(/*@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId*/) {
        return productsService.getAllProducts(1);
    }
    @Operation(summary = "Delete all products by id")
    @DeleteMapping("/all")
    public int deleteAllProduct(@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId) {
        return productsService.deleteAllProduct(1);
    }
    @Operation(summary = "Get popular product")
    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getPopularProduct() {
        return productsService.getPopularProduct(1).get(0);
    }
    @Operation(summary = "Get recommend product")
    @GetMapping("/recommend")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getRecommendProduct() {
        return productsService.getRecommendProduct().get(0);
    }
}
