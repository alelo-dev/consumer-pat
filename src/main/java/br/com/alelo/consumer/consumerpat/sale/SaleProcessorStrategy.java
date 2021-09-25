package br.com.alelo.consumer.consumerpat.sale;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.exception.ProductNotAllowedException;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleProcessorStrategy {

    List<SaleProcessor> saleProcessors;

    public Transaction processSale(final SaleData saleData) {
        final SaleProcessor processor = saleProcessors.stream()
                .filter(saleProcessor -> saleProcessor.supports(saleData.getProductType()))
                .findFirst()
                .orElseThrow(ProductNotAllowedException::new);

        return processor.process(saleData);
    }

}
