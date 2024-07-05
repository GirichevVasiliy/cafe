package com.girichev.cafe.product.service;

import com.girichev.cafe.product.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    ProductDto getProductById(Integer id);

    int deleteProduct(Integer id);

    List<ProductDto> getAllProducts(Integer typeId);

    int deleteAllProduct(Integer typeId);
}
