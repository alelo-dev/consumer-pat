package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ResultadoDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.vo.Buy;
import br.com.alelo.consumer.consumerpat.vo.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    public List<Consumer> listAllConsumers(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Consumer> consumer = repository.findAll(paging);
        if (consumer.hasContent()) {
            return consumer.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public ResultadoDTO createConsumer(Consumer consumer) {
        ResultadoDTO result = new ResultadoDTO();
        try {
            repository.save(consumer);
            result.setMessage("Consumer inserted with success");
            result.setError(false);
        } catch (Exception e){
            result.setMessage(Constants.ERROR_OCURRED);
            result.setError(true);
        }
        return result;
    }

    public ResultadoDTO updateConsumer(Consumer consumer) {
        Consumer findConsumer;
        ResultadoDTO result = new ResultadoDTO();
        try {
            Optional<Consumer> existsConsumer = repository.findById(consumer.getId());
            if (existsConsumer.isPresent()) {
                findConsumer = existsConsumer.get();
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
                result.setMessage("Update with success");
                result.setError(false);
            } else {
                result.setMessage(Constants.CONSUMER_NOT_FOUND);
                result.setError(true);
            }
        } catch (Exception e) {
            result.setMessage(Constants.ERROR_OCURRED);
            result.setError(true);
        }
        return result;
    }

    public ResultadoDTO setBalance(Cards cards) {
        ResultadoDTO result = new ResultadoDTO();
        Consumer consumer = repository.findByDrugstoreCardNumber(cards.getCardNumber());
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
                }
            }
        }
        if (consumer != null) {
            repository.save(consumer);
            result.setMessage("Balance updated with success");
            result.setError(false);
        } else {
            result.setMessage(Constants.CARD_NOT_FOUND);
            result.setError(true);
        }
        return result;
    }

    public ResultadoDTO buy(Buy buy) {
        ResultadoDTO result = new ResultadoDTO();
        Consumer consumer = null;
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
            double finalValue = 0;
            if (buy.getEstablishmentType() == 1) {
                // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                double cashback = (balanceValue / 100) * 10;
                finalValue = balanceValue + cashback;

                consumer = repository.findByFoodCardNumber(buy.getCards().getCardNumber());
                if (consumer != null) {
                    consumer.setFoodCardBalance(consumer.getFoodCardBalance() - finalValue);
                }
            } else if (buy.getEstablishmentType() == 2) {
                consumer = repository.findByDrugstoreCardNumber(buy.getCards().getCardNumber());
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
            }
            if (consumer != null) {
                repository.save(consumer);
                Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(), buy.getCards().getCardNumber(), finalValue);
                extractRepository.save(extract);
                result.setMessage("Buy executed with success");
                result.setError(false);
            } else {
                result.setMessage(Constants.INVALID_ESTABLISHMENT);
                result.setError(true);
            }
        } catch (Exception e) {
            result.setMessage(Constants.ERROR_OCURRED);
            result.setError(true);
        }
        return result;
    }

}
