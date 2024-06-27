package com.girichev.cafe.product.service;

import com.girichev.cafe.product.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto getProduct(Integer id);
    List<ProductDto> getAllProducts(Integer typeId);
}
