package com.inditex.price.application;


import com.inditex.price.domain.Price;
import com.inditex.price.domain.port.PriceQuery;
import com.inditex.price.exceptions.PriceNotFoundException;
import com.inditex.price.infrastructure.adapter.persistence.PriceEntity;
import com.inditex.price.infrastructure.adapter.persistence.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService implements PriceQuery {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        // Se obtienen los precios que cumplen con los criterios de búsqueda, ordenados por prioridad descendente.
        List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, date, date);

        // Si no se encuentran precios, se lanza una excepción
        if (prices.isEmpty()) {
            throw new PriceNotFoundException("No price found with given parameters");
        }

        // Se selecciona el primer resultado de la lista que está ordenada por prioridad.
        PriceEntity priceEntity = prices.get(0);


        return new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr());
    }
}
