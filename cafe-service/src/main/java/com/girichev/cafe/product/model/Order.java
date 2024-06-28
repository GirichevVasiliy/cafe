package com.girichev.cafe.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
@Table(name = "orders" , schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typeId")
    private Integer typeId;
    @Column(name = "total_size")
    private Integer totalSize;
    @Column(name = "off_set")
    private Integer offSet;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "type_id")
    @ToString.Exclude // Исключаем поле из toString
    @EqualsAndHashCode.Exclude // Исключаем поле из equals и hashCode
    private List<Product> products;
}
