package com.girichev.cafe.product.controller;

import com.girichev.cafe.product.dto.OrderDto;
import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {
    private static final String PRODUCT_ID_REQUEST_HEADER = "X-Product-Id";
    private final ProductsService productsService;

    /*    @Operation(summary = "Get product by id")
        @GetMapping()
        public ProductDto getProduct(@RequestHeader(PRODUCT_ID_REQUEST_HEADER) Integer id){
            return productsService.getProduct(id);
        }*/
    @Operation(summary = "Get all product by id")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getProductAll() {
        log.info("Connect!!!!!!!!!!");
        ProductDto productDto = ProductDto.builder()
                .id(1)
                .name("Sishi")
                .description("Good food")
                .price(100)
                .stars(5)
                .img("www.ya.ru")
                .location("Tymen")
                .created_at(LocalDate.now())
                .updated_at(LocalDate.now())
                .type_id(1)
                .build();
        OrderDto orderDto = OrderDto.builder()
                .type_id(1)
                .total_size(200)
                .off_set(56)
                .products(new ArrayList<>())
                .build();
        orderDto.getProducts().add(productDto);
        return orderDto;
    }
}
