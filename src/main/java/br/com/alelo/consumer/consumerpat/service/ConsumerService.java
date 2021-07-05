package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.ListUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumerService {

    private final @NonNull
    ConsumerRepository repository;

    public Consumer save(Consumer consumer) {
        return repository.save(consumer);
    }

    public Page<Consumer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Consumer update(Consumer consumer) throws BussinessException {
        Optional<Consumer> optionalConsumer = repository.findById(consumer.getId());

        if (optionalConsumer.isEmpty()) {
            throw new BussinessException("Cadastro não encontrado com id: " + consumer.getId());
        }
        Consumer consumerContext = optionalConsumer.get();

        if (isBalanceChanged(consumer.getCardList(), consumerContext.getCardList())) {
            throw new BussinessException("Não é permitido alterar o valor do cartão: " + consumer.getId());
        }

        return repository.save(consumer);
    }

    private boolean isBalanceChanged(Set<Card> cardList, Set<Card> cardListContext) {
        List<Double> allBalance = ListUtil
                .nonNull(cardList)
                .stream()
                .map(Card::getBalance)
                .collect(Collectors.toList());

        List<Double> allBalanceContext = ListUtil
                .nonNull(cardListContext)
                .stream()
                .map(Card::getBalance)
                .collect(Collectors.toList());

        return !allBalance.equals(allBalanceContext);
    }

    public Optional<Consumer> findOne(Long id) {
        return repository.findById(id);
    }
}
