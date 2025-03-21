package com.inditex.price.application.ports.out;

import com.inditex.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId);
}
