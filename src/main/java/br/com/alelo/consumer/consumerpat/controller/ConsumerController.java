package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.vo.Buy;
import br.com.alelo.consumer.consumerpat.vo.Cards;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.validateException;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumerList")
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() throws validateException {
        try {
            List<Consumer> consumer = repository.findAll();
            List<ConsumerDTO> consumers = consumer.stream().map(ConsumerDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(consumers);
        } catch (Exception e) {
            throw new validateException((Constants.ERROR_OCURRED));
        }
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity<Void> createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
        return ResponseEntity.ok().build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping("/updateConsumer")
    public ResponseEntity<Void> updateConsumer(@RequestBody Consumer consumer) throws validateException {
        try {
            Consumer findConsumer = repository.findConsumerById(consumer.getId());
            if (findConsumer != null) {
                findConsumer.setCountry(consumer.getCountry());
                findConsumer.setCity(consumer.getCity());
                findConsumer.setBirthDate(consumer.getBirthDate());
                findConsumer.setDocumentNumber(consumer.getDocumentNumber());
                findConsumer.setEmail(consumer.getEmail());
                findConsumer.setDrugstoreCardNumber(consumer.getDrugstoreCardNumber());
                findConsumer.setFoodCardNumber(consumer.getFoodCardNumber());
                findConsumer.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
                findConsumer.setFuelCardNumber(consumer.getFuelCardNumber());
                findConsumer.setName(consumer.getName());
                findConsumer.setNumber(consumer.getNumber());
                findConsumer.setPhoneNumber(consumer.getPhoneNumber());
                findConsumer.setPortalCode(consumer.getPortalCode());
                findConsumer.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
                findConsumer.setStreet(consumer.getStreet());
                repository.save(findConsumer);
                return ResponseEntity.ok().build();
            } else {
                throw new validateException(Constants.CONSUMER_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao atualizar os dados do cliente");
        }
        return null;
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping("/setcardbalance")
    public ResponseEntity<Void> setBalance(@RequestBody Cards cards) throws validateException {
        Consumer consumer;
        try {
            if (cards.getCardBalanceValue() > 0) {
                consumer = repository.findByDrugstoreNumber(cards.getCardNumber());
                if (consumer != null) {
                    // é cartão de farmácia
                    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + cards.getCardBalanceValue());
                } else {
                    consumer = repository.findByFoodCardNumber(cards.getCardNumber());
                    if (consumer != null) {
                        // é cartão de refeição
                        consumer.setFoodCardBalance(consumer.getFoodCardBalance() + cards.getCardBalanceValue());
                    } else {
                        // É cartão de combustivel
                        consumer = repository.findByFuelCardNumber(cards.getCardNumber());
                        if (consumer != null) {
                            consumer.setFuelCardBalance(consumer.getFuelCardBalance() + cards.getCardBalanceValue());
                        } else {
                            throw new validateException(Constants.CARD_NOT_FOUND);
                        }
                    }
                }
                repository.save(consumer);
                return ResponseEntity.ok().build();
            } else {
                throw new validateException(Constants.INVALID_VALUE);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao creditar(adicionar) um valor no cartão.");
        }
        return null;
    }

    @PostMapping("/buy")
    public void buy(@RequestBody Buy buy) throws validateException {
        Consumer consumer;
        double balanceValue = buy.getCards().getCardBalanceValue();
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        try {
            double finalValue;
            if (buy.getEstablishmentType() == 1) {
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                double cashback = (balanceValue / 100) * 10;
                finalValue = balanceValue + cashback;

                consumer = repository.findByFoodCardNumber(buy.getCards().getCardNumber());
                if (consumer != null) {
                    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - finalValue);
                }
            } else if (buy.getEstablishmentType() == 2) {
                consumer = repository.findByDrugstoreNumber(buy.getCards().getCardNumber());
                finalValue = balanceValue;
                if (consumer != null) {
                    consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - finalValue);
                }
            } else if (buy.getEstablishmentType() == 3) {
                // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                double tax = (balanceValue * 35) / 100;
                finalValue = balanceValue + tax;

                consumer = repository.findByFuelCardNumber(buy.getCards().getCardNumber());
                if (consumer != null) {
                    consumer.setFuelCardBalance(consumer.getFuelCardBalance() - finalValue);
                }
            } else {
                throw new validateException(Constants.INVALID_ESTABLISHMENT);
            }
            if (consumer != null) {
                repository.save(consumer);
                Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(), buy.getCards().getCardNumber(), finalValue);
                extractRepository.save(extract);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao efetuar o débito no estabelecimoento: " + buy.getEstablishmentName() + " !! Operação cancelada");
        }
    }
}
