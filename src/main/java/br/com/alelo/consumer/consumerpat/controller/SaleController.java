package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.SaleDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.sale.SaleData;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.EstablishmentService;
import br.com.alelo.consumer.consumerpat.service.SaleService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/establishment/{establishmentId}/sale")
public class SaleController {

    EstablishmentService establishmentService;

    CardService cardService;

    SaleService saleService;

    @PostMapping(value = "/product/{productId}/sell", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sell(
            final @PathVariable Long establishmentId,
            final @PathVariable int productId,
            final @RequestBody SaleDto saleDto
    ) {
        final ProductType productType = ProductType.of(productId)
                .orElseThrow(() -> new RestNotFoundException("Product not found"));
        final Establishment establishment = establishmentService.findById(establishmentId)
                .orElseThrow(() -> new RestNotFoundException("Establishment not found"));
        final Card card = cardService.findByNumberAndProductType(saleDto.getCardNumber(), productType)
                .orElseThrow(() -> new RestNotFoundException("Card not found for product ".concat(productType.name())));

        final SaleData saleData = SaleData.builder()
                .establishment(establishment)
                .productType(productType)
                .card(card)
                .productDescription(saleDto.getProductDescription())
                .saleValue(saleDto.getValue())
                .build();

        saleService.sell(saleData);
    }

}
