package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @Transactional
    public Consumer save(Consumer consumer) {
        return repository.save(consumer);
    }

    public void update(Consumer consumer) {

    }

    public void cardBalance(int cardNumber, double value) {
        Consumer consumer = extracted(cardNumber, value);
        this.save(consumer);
    }

    private Consumer extracted(int cardNumber, double value) {
        Optional<Consumer> consumer = repository.findByDrugstoreNumber(cardNumber);
        if (consumer.isPresent()){
            // é cartão de farmácia
            consumer.ifPresent(
                    c->c.setDrugstoreCardBalance(c.getDrugstoreCardBalance() + value)
            );
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer.isPresent()) {
                // é cartão de refeição
                consumer.ifPresent(
                      c -> c.setFoodCardBalance(c.getFoodCardBalance() + value)
                );
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.ifPresent(
                        c -> c.setFuelCardBalance(c.getFuelCardBalance() + value)
                );
            }
        }
        return consumer.get();
    }


    public void buy(int establishmentType, String establishmentName, int cardNumber,
                    String productDescription, double value) {
        switch (establishmentType) {
            case 1:
                value = getFood(cardNumber, value);
                break;
            case 2:
                value = getDrugStore(cardNumber, value);
                break;
            case 3:
                value = getFuel(cardNumber, value);
                break;
            default:
                break;
        }

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */


        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

    private double getFuel(int cardNumber, double value) {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;

        double tax = (value / 100) * 35;
        double valor = value + tax;
        Optional<Consumer> consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.ifPresent(
                c -> {
                    c.setFuelCardBalance(c.getFuelCardNumber() - value);
                    repository.save(consumer.get());
                }
        );
        return valor;
    }

    private double getDrugStore(int cardNumber, double value) {
        Optional<Consumer> consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.ifPresent(
                c -> {
                    c.setDrugstoreCardBalance(c.getDrugstoreCardBalance() - value);
                    repository.save(consumer.get());
                }
        );
        return value;
    }

    private double getFood(int cardNumber, double value) {
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        double cashback = (value / 100) * 10;
        double valor = value - cashback;
        Optional<Consumer> consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.ifPresent(
                c -> {
                    c.setFoodCardBalance(c.getFoodCardBalance() - valor);
                    repository.save(consumer.get());
                }
        );
        return valor;
    }


}
