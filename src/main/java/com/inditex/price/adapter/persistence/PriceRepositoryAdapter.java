package com.inditex.price.adapter.persistence;

import com.inditex.price.application.ports.out.PriceRepositoryPort;
import com.inditex.price.application.ports.out.PriceRepositoryPort;
import com.inditex.price.domain.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    //Busco el precio aplicable para un producto, marca y fecha dados.
    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        // Consulto la base de datos para obtener los precios que coincidan con los parámetros y prioridad descendente
        List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, date, date);

        //Si no hay precios aplicables devuelvo un optional vacio
        if (prices.isEmpty()) {
            return Optional.empty();
        }

        PriceEntity priceEntity = prices.get(0);
        return Optional.of(new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr()));
    }
}
