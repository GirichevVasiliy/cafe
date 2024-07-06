package com.girichev.cafe.product.service;

import com.girichev.cafe.excepton.BadRequestException;
import com.girichev.cafe.product.dto.ProductDto;
import com.girichev.cafe.product.mapper.ProductMapper;
import com.girichev.cafe.product.model.Product;
import com.girichev.cafe.product.storage.ProductsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductsServiceImpl implements ProductsService {
    ProductsRepository productsRepository;

    @Transactional
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        log.info("Add new product");
        Product product = ProductMapper.toEntity(productDto);
        try {
            return ProductMapper.toDto(productsRepository.save(product));
        } catch (DataIntegrityViolationException e) {
            log.warn("Product {} can't add to database", productDto);
            throw new BadRequestException(String.format("Product '%s' can't add to database", productDto));
        }
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("Update product");
        Product productUpdate = ProductMapper.toEntity(productDto);
        if (productsRepository.existsById(productDto.getId())) {
            try {
                return ProductMapper.toDto(productsRepository.save(productUpdate));
            } catch (DataIntegrityViolationException e) {
                log.warn("Product {} can't add to database", productDto);
                throw new BadRequestException(String.format("Product '%s' can't add to database", productDto));
            }
        } else {
            log.warn("Product {} can't add to database", productDto);
            throw new BadRequestException(String.format("Product '%s' can't add to database", productDto));
        }
    }

    @Override
    public ProductDto getProductById(Integer id) {
        log.info("Find product by id");
        return ProductMapper.toDto(productsRepository.getProductsById(id)
                .orElseThrow(() -> new BadRequestException(String.format("Product can't find by id '%s'", id))));
    }

    @Transactional
    @Override
    public int deleteProduct(Integer id) {
        return productsRepository.deleteProductById(id);
    }

    @Override
    public List<ProductDto> getAllProducts(Integer typeId) {
        log.info("Find all products by typeId");
        return productsRepository.getAllProductsByOrderId(typeId).stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public int deleteAllProduct(Integer typeId) {
        return productsRepository.deleteAllProductByTypeId(typeId);
    }

    @Override
    public List<ProductDto> getPopularProduct(Integer stars) {
        stars = 1;
        return productsRepository.getPopularProduct(stars).stream().map(ProductMapper::toDto).collect(Collectors.toList()); //TODO временно затычка
    }

    @Override
    public List<ProductDto> getRecommendProduct() {
       int stars = 0;
        return productsRepository.getPopularProduct(stars).stream().map(ProductMapper::toDto).collect(Collectors.toList()); //TODO временно затычка
    }
}
