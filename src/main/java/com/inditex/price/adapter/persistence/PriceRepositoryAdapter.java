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

    // Searching for the applicable price for a given product, brand, and date.
    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        // Query the database to retrieve prices that match the parameters, sorted by descending priority.
        List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, date, date);

        // If no applicable prices are found, return an empty optional.
        if (prices.isEmpty()) {
            return Optional.empty();
        }

        PriceEntity priceEntity = prices.get(0);
        return Optional.of(new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr()));
    }
}
