package com.girichev.cafe.order.storage;

import com.girichev.cafe.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    @Query("select ord from Order ord where ord.typeId = :typeId")
    Optional<Order> getOrderByTypeId(@Param("typeId") Integer typeId);
    @Modifying
    @Query("delete from Order ord where ord.typeId = :typeId")
    int deleteOrderByTypeId(@Param("typeId") Integer typeId);
}
