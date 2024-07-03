package com.girichev.cafe.product.mapper;

import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stars(productDto.getStars())
                .img(productDto.getImg())
                .location(productDto.getLocation())
                .createdAt(productDto.getCreated_at())
                .updatedAt(productDto.getUpdated_at())
                .typeId(productDto.getType_id())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stars(product.getStars())
                .img(product.getImg())
                .location(product.getLocation())
                .created_at(product.getCreatedAt())
                .updated_at(product.getUpdatedAt())
                .type_id(product.getTypeId())
                .build();
    }

}
