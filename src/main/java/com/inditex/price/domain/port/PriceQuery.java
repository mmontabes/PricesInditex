package com.inditex.price.domain.port;

import com.inditex.price.domain.Price;

import java.time.LocalDateTime;

public interface PriceQuery {
    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
