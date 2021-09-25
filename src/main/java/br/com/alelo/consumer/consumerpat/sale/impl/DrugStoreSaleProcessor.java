package br.com.alelo.consumer.consumerpat.sale.impl;

import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.sale.OwnValueSaleProcessor;
import org.springframework.stereotype.Component;

@Component
public class DrugStoreSaleProcessor extends OwnValueSaleProcessor {

    @Override
    public boolean supports(final ProductType productType) {
        return productType == ProductType.DRUGSTORE;
    }

}
