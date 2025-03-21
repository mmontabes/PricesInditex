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

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, date, date);

        if (prices.isEmpty()) {
            return Optional.empty();
        }

        PriceEntity priceEntity = prices.get(0);
        return Optional.of(new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr()));
    }
}
