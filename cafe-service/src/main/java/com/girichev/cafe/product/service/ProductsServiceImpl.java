package com.girichev.cafe.product.service;

import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.storage.ProductsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductsServiceImpl implements ProductsService {
    ProductsRepository productsRepository;

    @Override
    public ProductDto getProduct(Integer id) {
        return new ProductDto(1, "Pizza", "Very tasty pizza", 10, 5, "foto", "Tymen", LocalDate.now(), LocalDate.now(), 11);
    }

    @Override
    public List<ProductDto> getAllProducts(Integer typeId) {
        return null;
    }
}
