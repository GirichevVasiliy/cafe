package com.girichev.cafe.order.service;

import com.girichev.cafe.order.dto.OrderDto;

import java.util.List;

public interface OrdersService {
    OrderDto addOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    OrderDto getOrderById(Integer typeId);

    int deleteOrderById(Integer typeId);

    List<OrderDto> getAllOrders();

    List<OrderDto> getPopularOrder();

    List<OrderDto> getRecommendOrder();

}
