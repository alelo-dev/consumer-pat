package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;
    private final CardRepository cardRepository;
    private final ExtractService extractService;

    public Page<Consumer> findConsumersPageable(Pageable pageable) {
        log.info("Consulta consumers de forma paginada");
        return repository.findAll(pageable);
    }

    public Optional<Consumer> getById(Long id) {
        log.info("Consulta consumer por identificador: " + id);
        return repository.findById(id);
    }

    public void createConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = toEntity(consumerDTO);

        repository.save(consumer);
    }

    public void updateConsumer(Long id, ConsumerDTO consumerDTO) {
        Consumer consumer = repository.getReferenceById(id);

        repository.save(toEntity(consumerDTO));
    }

    public void setBalance(Long cardNumber, Double value) {
        Card card = cardRepository.findByNumber(cardNumber);

        if (card != null) {
            card.setBalance(card.getBalance() + value);
            cardRepository.save(card);
        }
    }

    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//        Consumer consumer;
//        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.
//
//         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
//         *
//         * Tipos dos estabelcimentos:
//         *    1) Alimentação (Food)
//         *    2) Farmácia (DrugStore)
//         *    3) Posto de combustivel (Fuel)
//         */
//
//        if (establishmentType == 1) {
//            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
//            double cashback = (value / 100) * 10;
//            value = value - cashback;
//
//            consumer = repository.findByFoodCardNumber(cardNumber);
//            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
//            repository.save(consumer);
//
//        } else if (establishmentType == 2) {
//            consumer = repository.findByDrugstoreNumber(cardNumber);
//            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
//            repository.save(consumer);
//
//        } else {
//            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
//            double tax = (value / 100) * 35;
//            value = value + tax;
//
//            consumer = repository.findByFuelCardNumber(cardNumber);
//            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
//            repository.save(consumer);
//        }
//
//        extractService.save(establishmentName, productDescription, cardNumber, value);
    }

    public Consumer toEntity(ConsumerDTO consumerDTO) {

        Contact contact = Contact.builder()
                .phoneNumber(consumerDTO.getPhoneNumber())
                .mobilePhoneNumber(consumerDTO.getMobilePhoneNumber())
                .residencePhoneNumber(consumerDTO.getPhoneNumber())
                .email(consumerDTO.getEmail())
                .build();

        Address address = Address.builder()
                .number(consumerDTO.getNumber())
                .city(consumerDTO.getCity())
                .country(consumerDTO.getCountry())
                .portalCode(consumerDTO.getPortalCode())
                .build();

        Card foodCard = Card.builder()
                .number(consumerDTO.getFoodCardNumber())
                .balance(consumerDTO.getFoodCardBalance())
                .type(CardTypeEnum.FOOD)
                .build();

        Card fuelCard = Card.builder()
                .number(consumerDTO.getFuelCardNumber())
                .balance(consumerDTO.getFuelCardBalance())
                .type(CardTypeEnum.FUEL)
                .build();

        Card drugstoreCard = Card.builder()
                .number(consumerDTO.getDrugstoreCardNumber())
                .balance(consumerDTO.getDrugstoreCardBalance())
                .type(CardTypeEnum.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .name(consumerDTO.getName())
                .documentNumber(consumerDTO.getDocumentNumber())
                .birthDate(consumerDTO.getBirthDate())
                .contact(contact)
                .address(address)
                .cards(Arrays.asList(foodCard, fuelCard, drugstoreCard))
                .build();

        return consumer;
    }
}
