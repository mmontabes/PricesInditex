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
            List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                            productId, brandId, date, date);
            if (prices.isEmpty()) {
                throw new PriceNotFoundException("No price found for the given parameters");
            }
            PriceEntity priceEntity = prices.get(0);
            return new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                    priceEntity.getPriceList(), priceEntity.getStartDate(),
                    priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr());
        }
    }

