package com.girichev.cafe.product.storage;

import com.girichev.cafe.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    @Query("select pr from Product pr where pr.id = :id")
    Optional<Product> getProductsById(@Param("id") Integer id);

    @Query("select pr from Product pr where pr.typeId = :typeId")
    List<Product> getAllProductsByOrderId(@Param("typeId") Integer typeId);

    @Modifying
    @Query("delete from Product pr where pr.id = :id")
    int deleteProductById(@Param("id") Integer id);

    @Modifying
    @Query("delete from Product pr where pr.typeId = :typeId")
    int deleteAllProductByTypeId(@Param("typeId") Integer typeId);
}
