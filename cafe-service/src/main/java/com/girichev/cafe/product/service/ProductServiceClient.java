package com.girichev.cafe.product.service;

import com.girichev.cafe.dto.ProductDto;

import java.util.List;

public interface ProductServiceClient {
    ProductDto getProduct(Integer id);
    List<ProductDto> getAllProduct(Integer typeId);
}
