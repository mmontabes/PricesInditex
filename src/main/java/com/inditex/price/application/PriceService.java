package com.inditex.price.application;

import com.inditex.price.application.ports.in.PriceQueryPort;
import com.inditex.price.application.ports.out.PriceRepositoryPort;
import com.inditex.price.domain.model.Price;
import com.inditex.price.exceptions.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService implements PriceQueryPort {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    // Obtiene el precio aplicable para un producto en una fecha y marca específicas.
    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepositoryPort.findApplicablePrice(date, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("No price found for the given parameters"));
    }
}
