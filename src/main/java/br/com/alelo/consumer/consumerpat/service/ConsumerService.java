package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
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

    public Consumer findByIdOrThrowBadRequestException(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Consumer not Found"));
    }

    @Transactional
    public Consumer save(ConsumerPostDTO consumerPostDTO) {
        Consumer consumer = new Consumer(consumerPostDTO);
        if(consumer.getName() != null && consumer.getEmail() != null && consumer.getMobilePhoneNumber() != 0) {
            this.repository.save(consumer);
        } else {
            throw new BadRequestException("Campos obrigatórios não informados");
        }
        return consumer;
    }

    public void update(ConsumerPutDTO consumerPutDTO) {
        Optional<Consumer> con = Optional.ofNullable(this.findByIdOrThrowBadRequestException(consumerPutDTO.getId()));
        Consumer consumer = new Consumer(consumerPutDTO);
        consumer.setId(con.map(c->c.getId()).get());
        consumer.setFoodCardBalance(con.map(c->c.getFoodCardBalance()).orElse(0.0));
        consumer.setDrugstoreCardBalance(con.map(c->c.getDrugstoreCardBalance()).orElse(0.0));
        consumer.setFuelCardBalance(con.map(c->c.getFuelCardBalance()).orElse(0.0));
        this.repository.save(consumer);
    }

    public void cardBalance(CardBalance cardBalance) {
        Optional<Consumer> consumer = repository.findByDrugstoreNumber(cardBalance.getCardNumber());
        if (consumer.isPresent()) {
            // é cartão de farmácia
            consumer.get().setDrugstoreCardBalance(consumer.map(c -> c.getDrugstoreCardBalance()).get() + cardBalance.getValue());

        } else {
            consumer = repository.findByFoodCardNumber(cardBalance.getCardNumber());
            if (consumer.isPresent()) {
                // é cartão de refeição
                consumer.get().setFoodCardBalance(consumer.map(c -> c.getFoodCardBalance()).get() + cardBalance.getValue());
                repository.save(consumer.get());
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardBalance.getCardNumber());
                consumer.ifPresent(
                        c -> {
                            c.setFuelCardBalance(c.getFuelCardBalance() + cardBalance.getValue());
                            repository.save(c);
                        });
            }
        }
    }


    public void buy(ExtractDTO extractDTO) {
        double value = 0.0;
        switch (extractDTO.getEstablishmentType()) {
            case 1: //food
                value = getFood(extractDTO.getCardNumber(), extractDTO.getValue());
                break;
            case 2: //drugStore
                value = getDrugStore(extractDTO.getCardNumber(), extractDTO.getValue());
                break;
            case 3: //fuel
                value = getFuel(extractDTO.getCardNumber(), extractDTO.getValue());
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


        Extract extract = new Extract(extractDTO.getEstablishmentName(), extractDTO.getProductDescription(),
                new Date(), extractDTO.getCardNumber(), value);
        extractRepository.save(extract);
    }

    private double getFuel(int cardNumber, double value) {
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;

        double tax = (value / 100) * 35;
        double valor = value + tax;
        Optional<Consumer> consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.ifPresent(
                c -> {
                    c.setFuelCardBalance(c.getFuelCardNumber() - valor);
                    repository.save(c);
                }
        );
        return valor;
    }

    private double getDrugStore(int cardNumber, double value) {
        Optional<Consumer> consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.ifPresent(
                c -> {
                    c.setDrugstoreCardBalance(c.getDrugstoreCardBalance() - value);
                    repository.save(c);
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
                    repository.save(c);
                }
        );
        return valor;
    }


}
