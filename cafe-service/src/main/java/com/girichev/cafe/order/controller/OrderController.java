package com.girichev.cafe.order.controller;

import com.girichev.cafe.order.dto.OrderDto;
import com.girichev.cafe.order.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public OrderDto addOrder(@Valid @RequestBody OrderDto orderDto) {
        return ordersService.addOrder(orderDto);
    }

    @Operation(summary = "Update order")
    @PatchMapping
    public OrderDto updateOrder(@Valid @RequestBody OrderDto orderDto) {
        return ordersService.updateOrder(orderDto);
    }

    @Operation(summary = "Get order by id")
    @GetMapping
    public OrderDto getOrderById(@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId) {
        return ordersService.getOrderById(typeId);
    }

    @Operation(summary = "Delete order by id")
    @DeleteMapping
    public int deleteOrderById(@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId) {
        return ordersService.deleteOrderById(typeId);
    }

    @Operation(summary = "Get all orders")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getProductAll() {
        OrderDto orderDto = ordersService.getAllOrders().get(0);
        return orderDto;
    }

    /* @Operation(summary = "Delete all products by id")
     @DeleteMapping("/all")
     public int deleteAllProduct(@RequestHeader(ORDER_TYPE_ID_REQUEST_HEADER) Integer typeId) {
         return productsService.deleteAllProduct(1);
     }*/
    @Operation(summary = "Get popular order")
    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getPopularOrder() {
        return ordersService.getPopularOrder().get(0);
    }

    @Operation(summary = "Get recommend order")
    @GetMapping("/recommend")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getRecommendOrder() {
        return ordersService.getRecommendOrder().get(0);
    }
}
