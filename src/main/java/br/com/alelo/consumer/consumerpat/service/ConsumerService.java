package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyRequest;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.dto.SetCardBalanceRequest;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardNotFound;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository repository;

    private final ExtractService extractService;

    public List<Consumer> listConsumer() {
        return repository.getAllConsumersList();
    }

    public void createorUpdateConsumer(ConsumerRequest consumerRequest) {
        Consumer consumer = new Consumer();

        if (Objects.nonNull(consumerRequest.getId())) {
            consumer.setId(consumerRequest.getId());
        }

        consumer.setDocumentNumber(consumerRequest.getDocumentNumber());
        consumer.setName(consumerRequest.getName());

        consumer.setBirthDate(consumerRequest.getBirthDate());

        //contacts
        consumer.setMobilePhoneNumber(consumerRequest.getMobilePhoneNumber());
        consumer.setResidencePhoneNumber(consumerRequest.getResidencePhoneNumber());
        consumer.setPhoneNumber(consumerRequest.getPhoneNumber());
        consumer.setEmail(consumerRequest.getEmail());

        //Address
        consumer.setStreet(consumerRequest.getStreet());
        consumer.setNumber(consumerRequest.getNumber());
        consumer.setCity(consumerRequest.getCity());
        consumer.setCountry(consumerRequest.getCountry());
        consumer.setPortalCode(consumerRequest.getPortalCode());

        //cards
        consumer.setFoodCardNumber(consumerRequest.getFoodCardNumber());
        consumer.setFoodCardBalance(consumerRequest.getFoodCardBalance());
        consumer.setFuelCardNumber(consumerRequest.getFuelCardNumber());
        consumer.setFuelCardBalance(consumerRequest.getFuelCardBalance());
        consumer.setDrugstoreNumber(consumerRequest.getDrugstoreNumber());
        consumer.setDrugstoreCardBalance(consumerRequest.getDrugstoreCardBalance());

        repository.save(consumer);
    }

    public void setBalance(SetCardBalanceRequest setCardBalanceRequest){
        Consumer consumer = new Consumer();
        consumer = repository.findByDrugstoreNumber(setCardBalanceRequest.getCardNumber());

        if (consumer != null) {
            // é cartão de farmácia
            saveDrugStore(consumer, setCardBalanceRequest.getValue());
            return;
        }

        consumer = repository.findByFoodCardNumber(setCardBalanceRequest.getCardNumber());
        if (consumer != null) {
            // é cartão de refeição
            saveFoodCardBalance(consumer, setCardBalanceRequest.getValue());
            return;
        }
        // É cartão de combustivel
        consumer = repository.findByFuelCardNumber(setCardBalanceRequest.getCardNumber());

        if (Objects.isNull(consumer)){
//            throw new CardNotFound("Cartão não encontrado");
            return;
        }
        saveFuelCardBalance(consumer, setCardBalanceRequest.getValue());
    }

    public void buy(BuyRequest buyRequest){
        Consumer consumer = new Consumer();
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        if (buyRequest.getEstablishmentType() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (buyRequest.getValue() / 100) * 10;
            buyRequest.setValue(buyRequest.getValue() - cashback);

            consumer = repository.findByFoodCardNumber(buyRequest.getCardNumber());
            if (Objects.isNull(consumer)){
                throw new CardNotFound("Cartão alimentacao nao encontrado");
            }
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - buyRequest.getValue());
            repository.save(consumer);

        } else if (buyRequest.getEstablishmentType() == 2) {
            consumer = repository.findByDrugstoreNumber(buyRequest.getCardNumber());
            if (Objects.isNull(consumer)){
                throw new CardNotFound("Cartão farmacia nao encontrado");
            }
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - buyRequest.getValue());
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (buyRequest.getValue() / 100) * 35;
            buyRequest.setValue(buyRequest.getValue() + tax);

            consumer = repository.findByFuelCardNumber(buyRequest.getCardNumber());
            if (Objects.isNull(consumer)){
                throw new CardNotFound("Cartão combustivel nao encontrado");
            }
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - buyRequest.getValue());
            repository.save(consumer);
        }
        extractService.save(buyRequest.getEstablishmentName(), buyRequest.getProductDescription(), buyRequest.getCardNumber(), buyRequest.getValue());
    }

    public void saveDrugStore(Consumer consumer, double value) {
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
        repository.save(consumer);
    }

    public void saveFoodCardBalance(Consumer consumer, double value) {
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
        repository.save(consumer);
    }

    public void saveFuelCardBalance(Consumer consumer, double value) {
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
        repository.save(consumer);
    }
}
