package com.inditex.price.adapter.rest;

import com.inditex.price.application.ports.in.PriceQueryPort;
import com.inditex.price.domain.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//Endpoint que permite obtener el precio aplicable para un producto en una fecha y marca específicas.
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceQueryPort priceQuery;

    @Autowired
    public PriceController(PriceQueryPort priceQuery) {
        this.priceQuery = priceQuery;
    }

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        // Llamo al servicio de aplicación para obtener el precio correspondiente
        Price price = priceQuery.getPrice(date, productId, brandId);
        // Devuelvo el precio en la respuesta
        return ResponseEntity.ok(price);
    }
}
