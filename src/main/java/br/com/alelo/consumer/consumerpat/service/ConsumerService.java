package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardBalanceChangeNotAllowedException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConsumerService extends BaseServiceImpl<Consumer, ConsumerRepository> {

    public ConsumerService(ConsumerRepository repository) {
        super(repository);
    }

    @Override
    public Consumer update(Long id, Consumer entity) {

        Optional<Consumer> consumer = findById(entity.getId());

        if (!entity.getCardList().isEmpty()) {
            for (Card card : entity.getCardList()) {
                if (getCardBalance(consumer, card.getId()).compareTo(card.getCardBalance()) != 0) {
                    throw new CardBalanceChangeNotAllowedException(card.getCardNumber());
                }
            }
        }

        return super.update(id, entity);
    }

    private BigDecimal getCardBalance(Optional<Consumer> consumer, Long id) {
        return consumer.get()
                .getCardList()
                .stream()
                .filter(cardDb -> cardDb.getId().compareTo(id) == 0)
                .findFirst().get()
                .getCardBalance();
    }
}
