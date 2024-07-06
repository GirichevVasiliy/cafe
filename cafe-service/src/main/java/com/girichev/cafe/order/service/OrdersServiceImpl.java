package com.girichev.cafe.order.service;

import com.girichev.cafe.excepton.BadRequestException;
import com.girichev.cafe.order.dto.OrderDto;
import com.girichev.cafe.order.mapper.OrderMapper;
import com.girichev.cafe.order.model.Order;
import com.girichev.cafe.order.storage.OrdersRepository;
import com.girichev.cafe.product.dto.ProductDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrdersServiceImpl implements OrdersService {
    OrdersRepository ordersRepository;

    @Transactional
    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        log.info("Add new order");
        Order order = OrderMapper.toEntity(orderDto);
        try {
            return OrderMapper.toDto(ordersRepository.save(order));
        } catch (DataIntegrityViolationException e) {
            log.warn("Order {} can't add to database", orderDto);
            throw new BadRequestException(String.format("Order '%s' can't add to database", orderDto));
        }
    }

    @Transactional
    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        log.info("Update order");
        return null;
    }

    @Override
    public OrderDto getOrderById(Integer typeId) {
        log.info("Get order by typeId {}", typeId);
        return OrderMapper.toDto(ordersRepository.getOrderByTypeId(typeId).orElseThrow(() -> new BadRequestException(String.format("Order can't find by id '%s'", typeId))));
    }

    @Transactional
    @Override
    public int deleteOrderById(Integer typeId) {
        log.info("Delete order by typeId {}", typeId);
        return ordersRepository.deleteOrderByTypeId(typeId);
    }

    @Override
    public List<OrderDto> getAllOrders() {
      return ordersRepository.getAllOrders().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getPopularOrder() {
        return ordersRepository.getAllOrders().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getRecommendOrder() {
        return ordersRepository.getAllOrders().stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }
}
