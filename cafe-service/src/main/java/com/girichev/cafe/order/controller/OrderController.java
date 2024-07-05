package com.girichev.cafe.order.controller;

import com.girichev.cafe.order.dto.OrderDto;
import com.girichev.cafe.order.service.OrdersService;
import com.girichev.cafe.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderController {
    private final OrdersService ordersService;
    private static final String ORDER_TYPE_ID_REQUEST_HEADER = "X-Order-Type-Id";
    @Operation(summary = "Add new order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto){
        return ordersService.addOrder(orderDto);
    }
    @Operation(summary = "Update order")
    @PatchMapping
    public OrderDto updateOrder(@Valid @RequestBody OrderDto orderDto){
        return ordersService.updateOrder(orderDto);
    }
    /*@Operation(summary = "Get order by id")
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
    public List<ProductDto> getProductAll(*//*@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId*//*) {
        return productsService.getAllProducts(1);
    }
    @Operation(summary = "Delete all products by id")
    @DeleteMapping("/all")
    public int deleteAllProduct(@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId) {
        return productsService.deleteAllProduct(1);
    }*/
}
