package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ExtractService extractService;

    @Override
    public List<Consumer> list() {
        return repository.findAll();
    }

    @Override
    public Consumer findById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public void update(Integer id, Consumer consumer) {
        Optional<Consumer> optCustomer = repository.findById(id);
        if (optCustomer.isEmpty())
            throw new RuntimeException("Consumer not found");
        Consumer currentConsumer = optCustomer.get();
        consumer.setFoodCardBalance(currentConsumer.getFoodCardBalance());
        consumer.setDrugstoreCardBalance(currentConsumer.getDrugstoreCardBalance());
        consumer.setFuelCardBalance(currentConsumer.getFuelCardBalance());
        repository.save(consumer);
    }

    @Override
    public Consumer create(Consumer consumer) {
        return repository.save(consumer);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }

    /**
     * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
     * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
     * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
     * cartão e alimentação Tipos de estabelcimentos 1 - Alimentação (food) 2 -
     * Farmácia (DrugStore) 3 - Posto de combustivel (Fuel)
     */
    @Override
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
            double value) {
        Consumer consumer = null;

        switch (establishmentType) {
        case 1:
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer == null) {
                throw new RuntimeException("EstablishmentType not found");
            }
            Double cashback = value * 0.1;
            value = value - cashback;

            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            break;
        case 2:
            consumer = repository.findByDrugstoreNumber(cardNumber);
            if (consumer == null) {
                throw new RuntimeException("EstablishmentType not found");
            }
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            break;
        case 3:
            consumer = repository.findByFuelCardNumber(cardNumber);
            if (consumer == null) {
                throw new RuntimeException("EstablishmentType not found");
            }
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = value * 0.35;
            value = value + tax;

            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            break;
        default:
            throw new RuntimeException("EstablishmentType not found");
        }
        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractService.save(extract);
        repository.save(consumer);
    }

}
