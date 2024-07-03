package com.girichev.cafe.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer type_id;
    private Integer total_size;
    private Integer off_set;
    private List<ProductDto> products;
}
