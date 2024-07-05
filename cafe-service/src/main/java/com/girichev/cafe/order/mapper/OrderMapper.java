package com.girichev.cafe.order.mapper;

import com.girichev.cafe.order.dto.OrderDto;
import com.girichev.cafe.order.model.Order;
import com.girichev.cafe.product.mapper.ProductMapper;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {
    public Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .typeId(orderDto.getType_id())
                .totalSize(orderDto.getTotal_size())
                .offSet(orderDto.getOff_set())
                .products(orderDto.getProducts().stream().map(ProductMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .type_id(order.getTypeId())
                .total_size(order.getTotalSize())
                .off_set(order.getOffSet())
                .products(order.getProducts().stream().map(ProductMapper::toDto).collect(Collectors.toList()))
                .build();
    }
}
