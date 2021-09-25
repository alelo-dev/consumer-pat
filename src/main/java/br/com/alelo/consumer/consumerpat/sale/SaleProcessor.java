package br.com.alelo.consumer.consumerpat.sale;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import org.springframework.stereotype.Component;

@Component
public interface SaleProcessor {

    boolean supports(final ProductType productType);

    Transaction process(final SaleData saleData);

}
