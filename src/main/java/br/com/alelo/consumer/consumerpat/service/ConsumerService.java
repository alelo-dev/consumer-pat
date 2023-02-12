package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.Mapper;
import br.com.alelo.consumer.consumerpat.dto.PageData;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ServiceException;
import br.com.alelo.consumer.consumerpat.exception.ServiceWarningException;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.card.CardOperationFactory;
import br.com.alelo.consumer.consumerpat.service.card.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.service.card.CardOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private CardOperationFactory cardFactory;

    @Autowired
    private Mapper mapper;

    @Transactional(readOnly = true)
    public PageData<Consumer> getAllConsumersListByPage(Pageable pageRequest) {

        Page<Consumer> pageConsumers = consumerRepository.getAllConsumersList(pageRequest);
        PageData<Consumer> pageDataCons = new PageData<>();

        pageDataCons.setRows(pageConsumers.getContent());
        pageDataCons.setCurrentPage(pageConsumers.getNumber());
        pageDataCons.setTotalItems(pageConsumers.getTotalElements());
        pageDataCons.setTotalPages(pageConsumers.getTotalPages());

        return pageDataCons;

    }

    @Transactional(rollbackFor = {ServiceException.class})
    public void saveConsumer(Consumer consumerSave) throws ServiceException, ServiceWarningException {

        Consumer consumer = null;

        if (consumerSave.getIdentifier() != null) {
            consumer = consumerRepository
                    .findByIdentifier(consumerSave.getIdentifier())
                    .orElseThrow(() -> new NotFoundException("Consumer not found"));
            consumerSave.setId(consumer.getId());
        }

        this.validateConsumerAlreadyExists(consumerSave);

        this.validateCardNumbers(consumerSave);

        if (consumerSave.getId() != null) {
            this.validateChangeCardNumberUpdate(consumer, consumerSave);
            this.updateConsumerFields(consumer, consumerSave);
        }else{
            consumerSave.setIdentifier(UUID.randomUUID().toString());
            consumer = consumerSave;
        }

        try {
            consumerRepository.save(consumer);
        }catch (Exception e) {
            log.error("Error while saving consumer", e);
            throw new ServiceException("Consumer could not be saved");
        }

    }

    private void validateConsumerAlreadyExists(Consumer consumer) throws ServiceWarningException {
        if (consumerRepository.existsByDocumentNumber(consumer.getDocumentNumber(),
                consumer.getId() != null? consumer.getId(): 0)) {
            throw new ServiceWarningException("Consumer already exists");
        }
    }

    private void validateCardNumbers(Consumer consumer) throws ServiceWarningException {

        List<Long> cardsNumber = new ArrayList<>();
        if (consumer.getFoodCardNumber() != null) {
            cardsNumber.add(consumer.getFoodCardNumber());
        }
        if (consumer.getDrugstoreNumber() != null) {
            cardsNumber.add(consumer.getDrugstoreNumber());
        }
        if (consumer.getFuelCardNumber() != null) {
            cardsNumber.add(consumer.getFuelCardNumber());
        }

        if (cardsNumber.size() == 0) {
            throw new ServiceWarningException("At least one type of card must be informed");
        }

        if (cardsNumber.stream()
                .distinct()
                .count() != cardsNumber.size()) {
            throw new ServiceWarningException("Cards' numbers must differ from one another");
        }

        cardsNumber.forEach(i -> {
            boolean exists = consumerRepository.existsByCardNumber(i, consumer.getId() != null ? consumer.getId() : 0);
            if (exists) {
                throw new ServiceWarningException("Card number " + i + " previously used by another consumer");
            }
        });

    }

    private void validateChangeCardNumberUpdate(Consumer consumerUpdate, Consumer consumer) throws ServiceWarningException {

        if (consumerUpdate.getFoodCardNumber() != null && consumer.getFoodCardNumber() != null) {
            if (consumerUpdate.getFoodCardNumber().compareTo(consumer.getFoodCardNumber()) != 0) {
                if (consumerUpdate.getFoodCardBalance().compareTo(BigDecimal.ZERO) > 0) {
                    throw new ServiceWarningException("Food card number already has a balance and cannot be changed");
                }
                boolean existsExtract = extractService.existsExtractByCardNumber(consumerUpdate.getFoodCardNumber());
                if (existsExtract) {
                    throw new ServiceWarningException("Food Card number is already on the extract and cannot be changed");
                }
            }
        }

        if (consumerUpdate.getDrugstoreNumber() != null && consumer.getDrugstoreNumber() != null) {
            if (consumerUpdate.getDrugstoreNumber().compareTo(consumer.getDrugstoreNumber()) != 0) {
                if (consumerUpdate.getDrugstoreCardBalance().compareTo(BigDecimal.ZERO) > 0) {
                    throw new ServiceWarningException("Drugstore card number already has a balance and cannot be changed");
                }
                boolean existsExtract = extractService.existsExtractByCardNumber(consumerUpdate.getDrugstoreNumber());
                if (existsExtract) {
                    throw new ServiceWarningException("Drugstore Card number is already on the extract and cannot be changed");
                }
            }
        }

        if (consumerUpdate.getFuelCardNumber() != null && consumer.getFuelCardNumber() != null) {
            if (consumerUpdate.getFuelCardNumber().compareTo(consumer.getFuelCardNumber()) != 0) {
                if (consumerUpdate.getFuelCardBalance().compareTo(BigDecimal.ZERO) > 0) {
                    throw new ServiceWarningException("Fuel card number already has a balance and cannot be changed");
                }
                boolean existsExtract = extractService.existsExtractByCardNumber(consumerUpdate.getFuelCardNumber());
                if (existsExtract) {
                    throw new ServiceWarningException("Fuel Card number is already on the extract and cannot be changed");
                }
            }
        }

    }

    private void updateConsumerFields(Consumer consumerUpdate, Consumer consumer) {

        consumerUpdate.setName(consumer.getName());
        consumerUpdate.setDocumentNumber(consumer.getDocumentNumber());
        consumerUpdate.setBirthDate(consumer.getBirthDate());
        consumerUpdate.setMobilePhoneNumber(consumer.getMobilePhoneNumber());
        consumerUpdate.setResidencePhoneNumber(consumer.getResidencePhoneNumber());
        consumerUpdate.setPhoneNumber(consumer.getPhoneNumber());
        consumerUpdate.setEmail(consumer.getEmail());
        consumerUpdate.setStreet(consumer.getStreet());
        consumerUpdate.setNumber(consumer.getNumber());
        consumerUpdate.setCity(consumer.getCity());
        consumerUpdate.setCountry(consumer.getCountry());
        consumerUpdate.setPostalCode(consumer.getPostalCode());
        consumerUpdate.setFoodCardNumber(consumer.getFoodCardNumber());
        consumerUpdate.setFuelCardNumber(consumer.getFuelCardNumber());
        consumerUpdate.setDrugstoreNumber(consumer.getDrugstoreNumber());

    }

    @Transactional(rollbackFor = {ServiceException.class})
    public Consumer credit(long cardNumber, BigDecimal value) throws ServiceException, NotFoundException {

        Consumer consumer = consumerRepository.findByCardNumber(cardNumber, 0)
                .orElseThrow(() -> new NotFoundException("Consumer not found"));

        CardOperation card = cardFactory.findType(consumer, cardNumber);

        try {
            consumer = card.credit(consumer, value);
            return consumer;
        }catch(ServiceException | NotFoundException e){
            throw e;
        }catch(Exception e) {
            log.error("Error while saving credit", e);
            throw new ServiceException("The balance change could not be completed");
        }

    }

    /**
     * Débito de valor no cartão (compra) <br/><br/>
     *
     * establishmentType: tipo do estabelecimento comercial <br/>
     * establishmentName: nome do estabelecimento comercial <br/>
     * cardNumber: número do cartão <br/>
     * productDescription: descrição do produto <br/>
     * value: valor a ser debitado (subtraído)
     **/
    @Transactional(rollbackFor = {ServiceException.class})
    public Consumer debit(CardTypeEnum establishmentType, int establishmentNameId, String establishmentName,
                          long cardNumber, String productDescription,
                          BigDecimal value) throws ServiceException, NotFoundException, InsufficientBalanceException {

        CardOperation card = cardFactory.findType(establishmentType);

        try {
            value = card.calculateDebitValue(value);
            Consumer consumer = card.debit(cardNumber, value);

            Extract extract = new Extract(establishmentNameId, establishmentName, productDescription,
                    LocalDateTime.now(), cardNumber, value);
            extractService.saveExtract(extract);

            return consumer;
        }catch(ServiceException | NotFoundException | InsufficientBalanceException e){
            throw e;
        }catch(Exception e) {
            log.error("Error while saving debit", e);
            throw new ServiceException("The purchase could not be completed");
        }

    }

}
