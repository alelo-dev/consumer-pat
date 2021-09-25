package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.respository.TransactionRepository;
import br.com.alelo.consumer.consumerpat.sale.SaleData;
import br.com.alelo.consumer.consumerpat.sale.SaleProcessorStrategy;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleService {

    CardService cardService;

    TransactionRepository transactionRepository;

    SaleProcessorStrategy saleProcessorStrategy;

    public Transaction sell(final SaleData saleData) {
        final Transaction transaction = saleProcessorStrategy.processSale(saleData);
        cardService.save(saleData.getCard());
        return transactionRepository.save(transaction);
    }

}
