package com.inditex.price.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor

public class Price {

        private Long productId;
        private Long brandId;
        private Integer priceList;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private BigDecimal price;
        private String curr;


}

