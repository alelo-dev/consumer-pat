package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository repository;

    public void setBalance(int cardNumber, double value) {

        var consumer = repository.findByCardsDrugstoreNumber(cardNumber);

        if(consumer.isPresent()) {
            // é cartão de farmácia
            consumer.get().getCards().setDrugstoreCardBalance(consumer.get().getCards().getDrugstoreCardBalance() + value);
            repository.save(consumer.get());
        } else {
            consumer = repository.findByCardsFoodCardNumber(cardNumber);
            if(consumer.isPresent()) {
                // é cartão de refeição
                consumer.get().getCards().setFoodCardBalance(consumer.get().getCards().getFoodCardBalance() + value);
                repository.save(consumer.get());
            } else {
                // É cartão de combustivel
                consumer = repository.findByCardsFuelCardNumber(cardNumber);
                consumer.get().getCards().setFuelCardBalance(consumer.get().getCards().getFuelCardBalance() + value);
                repository.save(consumer.get());
            }
        }

    }

    @Override
    public void save(Consumer consumer) {
        repository.save(consumer);
    }

    @Override
    public List<Consumer> listAllConsumers() {
        return repository.findAll();
    }

    @Override
    public double buy(EstablishmentType establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Optional<Consumer> consumer;

        if (establishmentType == EstablishmentType.Food) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByCardsFoodCardNumber(cardNumber);
            consumer.get().getCards().setFoodCardBalance(consumer.get().getCards().getFoodCardBalance() - value);
            repository.save(consumer.get());

        }else if(establishmentType == EstablishmentType.Drugstore) {
            consumer = repository.findByCardsDrugstoreNumber(cardNumber);
            consumer.get().getCards().setDrugstoreCardBalance(consumer.get().getCards().getDrugstoreCardBalance() - value);
            repository.save(consumer.get());

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByCardsFuelCardNumber(cardNumber);
            consumer.get().getCards().setFuelCardBalance(consumer.get().getCards().getFuelCardBalance() - value);
            repository.save(consumer.get());
        }

        return value;
    }
}
