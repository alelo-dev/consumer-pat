package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractService extractService;

    @Override
    public List<ConsumerResponseDTO> getAllConsumersList() {
        List<Consumer> consumers = consumerRepository.findAll();
        List<ConsumerResponseDTO> consumerResponseDTOS = new ArrayList<>();
        consumers.forEach(consumer ->{
            ConsumerResponseDTO consumerResponseDTO = ConsumerResponseDTO.builder().
                    id(consumer.getId()).name(consumer.getName()).birthDate(consumer.getBirthDate()).documentNumber(consumer.getDocumentNumber()).build();
            consumerResponseDTOS.add(consumerResponseDTO);
        });
        return consumerResponseDTOS;
    }

    @Override
    public ConsumerResponseDTO saveConsumer(Consumer consumer) {
        consumer = consumerRepository.save(consumer);
        return ConsumerResponseDTO.builder().id(consumer.getId()).name(consumer.getName()).birthDate(consumer.getBirthDate())
                .documentNumber(consumer.getDocumentNumber()).build();
    }

    @Override
    public Consumer findByFoodCardNumber(Integer cardNumber) {
        return consumerRepository.findByFoodCardNumber(cardNumber);
    }

    @Override
    public Consumer findByFuelCardNumber(Integer cardNumber) {
        return consumerRepository.findByFuelCardNumber(cardNumber);
    }

    @Override
    public Consumer findByDrugstoreNumber(Integer cardNumber) {
        return consumerRepository.findByDrugstoreNumber(cardNumber);
    }


    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    @Override
    public ExtractResponseDTO buy(Integer establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value) {
        Consumer consumer = null;

        if (establishmentType == 1) {
            value = getFoodCardNumber(cardNumber, value);
        } else if (establishmentType == 2) {
            getDrugstoreNumber(cardNumber, value);
        } else {
            value = getFuelCardNumber(cardNumber, value);
        }
        Extract extract = saveExtract(establishmentName, cardNumber, productDescription, value);
        return getBuildExtract(extract);

    }

    private ExtractResponseDTO getBuildExtract(Extract extract) {
        return ExtractResponseDTO.builder().establishmentName(extract.getEstablishmentName())
                .establishmentNameId(extract.getEstablishmentNameId()).
                productDescription(extract.getProductDescription()).dateBuy(extract.getDateBuy()).
                cardNumber(extract.getCardNumber()).value(extract.getValue()).build();
    }

    private Extract saveExtract(String establishmentName, Integer cardNumber, String productDescription, Double value) {
        Extract extract = extractService.saveExtract(Extract.builder().establishmentName(establishmentName).productDescription(productDescription)
                .dateBuy(new Date()).cardNumber(cardNumber).value(value).build());
        return extract;
    }

    private Double getFuelCardNumber(Integer cardNumber, Double value) {
        Consumer consumer;
        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
        Double tax = (value / 100) * 35;
        value = value + tax;

        consumer = findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
        saveConsumer(consumer);
        return value;
    }

    private void getDrugstoreNumber(Integer cardNumber, Double value) {
        Consumer consumer;
        consumer = findByDrugstoreNumber(cardNumber);
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
        saveConsumer(consumer);
    }

    private Double getFoodCardNumber(Integer cardNumber, Double value) {
        Consumer consumer;
        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
        Double cashback = (value / 100) * 10;
        value = value - cashback;

        consumer = findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
        saveConsumer(consumer);
        return value;
    }

    @Override
    public ConsumerResponseDTO setCardBalence(Integer cardNumber, Double value) {
        Consumer consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        return getCard(cardNumber, value, consumer);
    }

    private ConsumerResponseDTO getCard(Integer cardNumber, Double value, Consumer consumer) {
        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            return saveConsumer(consumer);
        } else {
            consumer = findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                return saveConsumer(consumer);
            } else {
                // É cartão de combustivel
                consumer = findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                return saveConsumer(consumer);
            }
        }
    }

}
