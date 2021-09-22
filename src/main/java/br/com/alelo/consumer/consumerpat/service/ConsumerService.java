package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;

    public Page<Consumer> getAllConsumersList(Pageable pagination) {
        return consumerRepository.findAll(pagination);
    }

    @Transactional
    public Consumer createNewConsumer(Consumer consumer) {
        cardNumberAvailability(consumer);
        consumerRepository.save(consumer);

        return consumer;
    }

    @Transactional
    public void updateConsumer(Consumer consumer) {
        Optional<Consumer> actualConsumer = Optional.ofNullable(consumerRepository.findById(consumer.getId())
                .orElseThrow(() -> new IllegalArgumentException(("O usuário com ID : " + consumer.getId() + " não existe"))));

        updateConsumer(consumer, actualConsumer);
    }

    @Transactional
    public void updateBalance(Long cardNumber, double value) {
        Consumer consumer = getConsumerByCard(cardNumber);
        updateCardBalance(cardNumber, value, consumer);
    }

    @Transactional
    public void registerSale(String establishmentName, Long cardNumber, String productDescription, double value) {
        Consumer consumer = getConsumerByCard(cardNumber);
        updateBalanceAndExtract(consumer, establishmentName, cardNumber, productDescription, value);
    }

    private void updateConsumer(Consumer consumer, Optional<Consumer> actualConsumer) {

        cardNumberAvailability(consumer);
        if(actualConsumer.isPresent()) {

            actualConsumer.get().setName(consumer.getName());
            actualConsumer.get().setDocumentNumber(consumer.getDocumentNumber());
            actualConsumer.get().setBirthDate(consumer.getBirthDate());
            actualConsumer.get().setMobilePhoneNumber(consumer.getMobilePhoneNumber());
            actualConsumer.get().setResidencePhoneNumber(consumer.getResidencePhoneNumber());
            actualConsumer.get().setPhoneNumber(consumer.getPhoneNumber());
            actualConsumer.get().setEmail(consumer.getEmail());
            actualConsumer.get().setAddressStreet(consumer.getAddressStreet());
            actualConsumer.get().setAddressNumber(consumer.getAddressNumber());
            actualConsumer.get().setCity(consumer.getCity());
            actualConsumer.get().setCountry(consumer.getCountry());
            actualConsumer.get().setPostalCode(consumer.getPostalCode());
            actualConsumer.get().setFoodCardNumber(consumer.getFoodCardNumber());
            actualConsumer.get().setDrugstoreCardNumber(consumer.getDrugstoreCardNumber());
            actualConsumer.get().setFuelCardNumber(consumer.getFuelCardNumber());

            consumerRepository.save(consumer);

        }
    }

    private void updateCardBalance(Long cardNumber, double value, Consumer consumer) {

        if(consumer.getDrugstoreCardNumber() == cardNumber) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().add(new BigDecimal(value)));
            consumerRepository.save(consumer);
        } else if(consumer.getFoodCardNumber() == cardNumber) {
            // é cartão de refeição
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().add(new BigDecimal(value)));
            consumerRepository.save(consumer);
        } else {
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().add(new BigDecimal(value)));
            consumerRepository.save(consumer);
        }
    }

    private void updateBalanceAndExtract(Consumer consumer, String establishmentName, Long cardNumber, String productDescription, double value) {

        if (consumer.getFoodCardNumber() == cardNumber) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            BigDecimal cashbackValue = DataUtils.getCashbackValue(value);

            consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(cashbackValue));
            consumerRepository.save(consumer);

        } else if(consumer.getDrugstoreCardNumber() == cardNumber) {
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().subtract(new BigDecimal(value)));
            consumerRepository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            BigDecimal taxValue = DataUtils.increaseTaxValue(value);

            consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(taxValue));
            consumerRepository.save(consumer);
        }
        extractRepository.save(new Extract(establishmentName, productDescription, new Date(), cardNumber, new BigDecimal(value)));
    }

    private Consumer getConsumerByCard(Long cardNumber) {
        Consumer consumer;
        consumer = consumerRepository.findConsumerByCard(cardNumber);

        if(consumer == null) {
            throw new IllegalArgumentException(("O cartão com número : " + cardNumber + " não existe"));
        }

        return consumer;
    }

    private void cardNumberAvailability(Consumer consumer) {
        List<Consumer> consumersList = consumerRepository.findConsumerByCardType(consumer.getFoodCardNumber(), consumer.getFuelCardNumber(), consumer.getDrugstoreCardNumber());

        if(consumersList.stream().anyMatch(c -> c.getFoodCardNumber().equals(consumer.getFoodCardNumber()))) {
            throw new IllegalArgumentException(("O cartão com número : " + consumer.getFoodCardNumber() + " já está em uso."));
        } else if(consumersList.stream().anyMatch(c -> c.getFuelCardNumber().equals(consumer.getFuelCardNumber()))) {
            throw new IllegalArgumentException(("O cartão com número : " + consumer.getFuelCardNumber() + " já está em uso."));
        } else if(consumersList.stream().anyMatch(c -> c.getDrugstoreCardNumber().equals(consumer.getDrugstoreCardNumber()))){
            throw new IllegalArgumentException(("O cartão com número : " + consumer.getDrugstoreCardNumber() + " já está em uso."));
        }
    }
}
