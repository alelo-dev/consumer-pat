package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumeration.CardEstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.excepion.CardNotFoundExeption;
import br.com.alelo.consumer.consumerpat.excepion.NoBalanceException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;

@Log4j2
@Service
public class BalanceService {

    private Map<CardEstablishmentTypeEnum, BiFunction<Long, Double, Consumer>> buyActions;
    private List<BiFunction<Long, Double, Consumer>> creditActions;

    private ConsumerRepository repository;
    private ExtractRepository extractRepository;

    public BalanceService(ConsumerRepository repository, ExtractRepository extractRepository) {
        this.repository = repository;
        this.extractRepository = extractRepository;
        buyActions = new HashMap<>();
        buyActions.put(CardEstablishmentTypeEnum.FOOD, (Long cardNumber, Double value) -> {
            log.info("try to by food cardNumber: {}, value: {}", cardNumber, value);
            var cashback = (value / 100) * 10;
            value = value - cashback;
            var consumer = repository.findFirstByFoodCardNumber(cardNumber);
            validateConsumer(consumer);
            validateBalanceDebit(consumer.getFoodCardBalance(), value);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            return consumer;
        });
        buyActions.put(CardEstablishmentTypeEnum.DRUG_SOTRE, (Long cardNumber, Double value) -> {
            var consumer = repository.findFirstByDrugstoreNumber(cardNumber);
            validateConsumer(consumer);
            validateBalanceDebit(consumer.getDrugstoreCardBalance(), value);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            return consumer;
        });
        buyActions.put(CardEstablishmentTypeEnum.FUEL, (Long cardNumber, Double value) -> {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            var tax = (value / 100) * 35;
            value = value + tax;
            var consumer = repository.findFirstByFuelCardNumber(cardNumber);
            validateConsumer(consumer);
            validateBalanceDebit(consumer.getFuelCardBalance(), value);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            return consumer;
        });

        creditActions = new ArrayList<>();
        creditActions.add((Long cardNumber, Double value) -> {
            log.info("try to set  setDrugstoreCardBalance ");//TODO: retirar os logs depois de validado
            return  Optional.ofNullable(repository.findFirstByDrugstoreNumber(cardNumber)).map(consumer1 -> {
            consumer1.setDrugstoreCardBalance(consumer1.getDrugstoreCardBalance() + value);
            return consumer1;
        }).orElse(null); });
        creditActions.add((Long cardNumber, Double value) -> {
            log.info("try to set setFoodCardBalance ");
            return  Optional.ofNullable(repository.findFirstByFoodCardNumber(cardNumber)).map(consumer1 -> {
            consumer1.setFoodCardBalance(consumer1.getFoodCardBalance() + value);
            return consumer1;
        }).orElse(null); });
        creditActions.add((Long cardNumber, Double value) -> {
            log.info("try to set  setFuelCardBalance ");
            return  Optional.ofNullable(repository.findFirstByFuelCardNumber(cardNumber)).map(consumer1 -> {
            consumer1.setFuelCardBalance(consumer1.getFuelCardBalance() + value);
            return consumer1;
        }).orElse(null); });
    }

    public void setCredit(Long cardNumber, Double value) {
        creditActions.stream()
                .map(function -> function.apply(cardNumber, value) )
                .filter(Objects::nonNull)
                .findFirst()
                .map(consumer1 -> repository.save(consumer1))
                .orElseThrow(CardNotFoundExeption::new);
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    public void buy(final Integer establishmentType, final String establishmentName, final Long cardNumber, final String productDescription, final Double value) {
        log.info("Tentativa de compra para: establishmentType: {}, establishmentName: {}, cardNumber: {}, productDescription: {}, value: {}", establishmentType, establishmentName, cardNumber, productDescription, value );
        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
         *
         * Tipos dos estabelcimentos:
         *    1) Alimentação (Food)
         *    2) Farmácia (DrugStore)
         *    3) Posto de combustivel (Fuel)
         */
        Consumer consumer = CardEstablishmentTypeEnum.fromValue(establishmentType)
                .map(typeEnum -> buyActions.get(typeEnum))
                .map(function -> function.apply(cardNumber, value))
                .orElseThrow(CardNotFoundExeption::new);
        saveConsumerAndExtract(consumer, establishmentName, productDescription, cardNumber, value);
    }

    private void validateConsumer(Consumer consumer) {
        if (Objects.isNull(consumer))
            throw new CardNotFoundExeption();
    }

    private void validateBalanceDebit(Double balance, Double debitValue) {
        if (balance - debitValue < 0.0)
            throw new NoBalanceException();
    }

    private void saveConsumerAndExtract(Consumer consumer, String establishmentName, String productDescription, Long cardNumber, Double value) {
        repository.save(consumer);
        Extract extract = Extract.builder()
                .establishmentName(establishmentName)
                .productDescription(productDescription)
                .cardNumber(cardNumber)
                .amount(value)
                .dateBuy(new Date())
                .build();
        extractRepository.save(extract);
    }

    public Page<Extract> findAllExtract(Pageable pageable){
        return extractRepository.findAll(pageable);
    }

}
