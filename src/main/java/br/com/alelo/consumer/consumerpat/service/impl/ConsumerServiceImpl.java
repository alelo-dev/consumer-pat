package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.TipoCartao;
import br.com.alelo.consumer.consumerpat.exception.RegraNegocioException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;

    @Override
    public List<Consumer> listAllConsumers() {
        List<Consumer> allConsumersList = repository.getAllConsumersList();
        if (CollectionUtils.isEmpty(allConsumersList)) {
            return Collections.emptyList();
        }
        return allConsumersList;
    }

    @Override
    @Transactional
    public void createConsumer(Consumer consumer) {
        repository.save(consumer);
    }

    @Override
    @Transactional
    public void updateConsumer(Integer id, Consumer consumer) {
        repository
                .findById(id)
                .map(c -> {
                    consumer.setId(c.getId());
                    repository.save(consumer);
                    return c;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Override
    @Transactional
    public void setBalance(CardDTO cardDTO) {
        Consumer consumer = null;
        boolean acaoRealizada = false;

        if (cardDTO.getCardNumber().equals(TipoCartao.FOOD.getValue())) {
            consumer = repository.findByFoodCardNumber(cardDTO.getCardNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + cardDTO.getValue());
                repository.save(consumer);
                acaoRealizada = true;
            }

        } else if (cardDTO.getCardNumber().equals(TipoCartao.DRUGSTORE.getValue())) {
            consumer = repository.findByDrugstoreNumber(cardDTO.getCardNumber());
            if(consumer != null) {
                // é cartão de farmácia
                consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + cardDTO.getValue());
                repository.save(consumer);
                acaoRealizada = true;
            }

        } else if (cardDTO.getCardNumber().equals(TipoCartao.FUEL.getValue())) {
            consumer = repository.findByFoodCardNumber(cardDTO.getCardNumber());
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + cardDTO.getValue());
                repository.save(consumer);
                acaoRealizada = true;
            }

        } else {
            throw new RegraNegocioException("Não é possivel efetuar credito, cartão não encontrado.");
        }

    }

    @Override
    @Transactional
    public void buy(BuyDTO buyDTO) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        if (buyDTO.getEstablishmentType().equals(TipoCartao.FOOD.getValue())) {
            saveBuyFood(buyDTO.getCardNumber(), buyDTO.getValue());

        } else if(buyDTO.getEstablishmentType().equals(TipoCartao.DRUGSTORE.getValue())) {
            saveBuyDrugstore(buyDTO.getCardNumber(), buyDTO.getValue());

        } else {
            saveBuyFuel(buyDTO.getCardNumber(), buyDTO.getValue());
        }
        Extract extract = new Extract(buyDTO.getEstablishmentName(), buyDTO.getProductDescription(), new Date(), buyDTO.getCardNumber(), buyDTO.getValue());
        extractRepository.save(extract);
    }

    private void saveBuyFood(int cardNumber, double value) {
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        Double cashback  = (value / 100) * 10;
        value = value - cashback;

        Consumer consumer = repository.findByFoodCardNumber(cardNumber);
        if (consumer != null) {
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);
        }
    }

    private void saveBuyDrugstore(int cardNumber, double value) {
        Consumer consumer = repository.findByDrugstoreNumber(cardNumber);
        if (consumer != null) {
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);
        }
    }

    private void saveBuyFuel(int cardNumber, double value) {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        Double tax  = (value / 100) * 35;
        value = value + tax;
        Consumer consumer = repository.findByFuelCardNumber(cardNumber);
        if (consumer != null) {
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }
    }
}
