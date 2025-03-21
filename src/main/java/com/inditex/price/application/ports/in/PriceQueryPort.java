package com.inditex.price.application.ports.in;

import com.inditex.price.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceQueryPort {
    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
