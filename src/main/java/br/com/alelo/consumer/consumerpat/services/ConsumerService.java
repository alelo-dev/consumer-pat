package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.dtos.Buy;
import br.com.alelo.consumer.consumerpat.dtos.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entities.Consumer;
import br.com.alelo.consumer.consumerpat.entities.Extract;
import br.com.alelo.consumer.consumerpat.respositories.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respositories.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ExtractRepository extractRepository;

    @Autowired
    public ConsumerService(ConsumerRepository consumerRepository, ExtractRepository extractRepository) {
        this.consumerRepository = consumerRepository;
        this.extractRepository = extractRepository;
    }

    public List<Consumer> getConsumersList() {
        return consumerRepository.findAll();
    }

    public void saveConsumer(ConsumerDto consumerDto) {
        consumerRepository.save(new Consumer(consumerDto));
    }

    public void updateConsumer(ConsumerDto consumerDto) {
        consumerDto.setDrugstoreCardBalance(null);
        consumerDto.setFoodCardBalance(null);
        consumerDto.setFuelCardBalance(null);
        consumerRepository.save(new Consumer(consumerDto));
    }

    public void setBalance(int cardNumber, BigDecimal value) {
        Consumer consumer = consumerRepository.findOneByCardNumber(cardNumber);
        if (consumer != null) {
            consumer.getCards().setBalance(value);
            consumerRepository.save(consumer);
        }
    }

    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    public void buy(Buy buy) {
        Consumer consumer = consumerRepository.findOneByCardNumber(buy.getCardNumber());
        consumer.getCards().setBuyBalance(buy.getValue());
        consumerRepository.save(consumer);
        Extract extract = new Extract(
                null,
                buy.getEstablishmentType(),
                buy.getEstablishmentName(),
                buy.getProductDescription(),
                LocalDateTime.now(),
                buy.getCardNumber(),
                buy.getValue()
        );
        extractRepository.save(extract);
    }

}
