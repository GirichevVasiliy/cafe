package com.girichev.cafe.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Integer id;
    @Column(name = "name", unique = true, nullable = false)
    String name;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "price", nullable = false)
    Integer price;
    @Column(name = "stars")
    Integer stars;
    @Column(name = "img", nullable = false)
    String img;
    @Column(name = "location", nullable = false)
    String location;
    @Column(name = "createdAt", nullable = false)
    LocalDate createdAt;
    @Column(name = "updatedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate updatedAt;
    @Column(name = "typeId")
    Integer typeId;
}
