package com.girichev.cafe.order.service;

import com.girichev.cafe.order.dto.OrderDto;

public interface OrdersService {
    OrderDto addOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    OrderDto getOrderById(Integer typeId);

    int deleteOrderById(Integer typeId);
}
