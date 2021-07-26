package br.com.alelo.consumer.consumerpat.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 08:24
 */

import br.com.alelo.consumer.consumerpat.business.service.ConsumerBService;
import br.com.alelo.consumer.consumerpat.business.service.impl.AddressBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.business.service.impl.ContactBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerAddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerContactDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.page.LinkDTO;
import br.com.alelo.consumer.consumerpat.model.dto.page.MetaDTO;
import br.com.alelo.consumer.consumerpat.model.dto.page.PageDTO;
import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ConsumerRecoveryBObject {

    private final ConsumerBService consumerBService;
    private final ContactBServiceImpl contactBServiceImpl;
    private final AddressBServiceImpl addressBServiceImpl;
    private final AleloCardBServiceImpl aleloCardBService;

    public ConsumerRecoveryBObject(ConsumerBService consumerBService,
                                   ContactBServiceImpl contactBServiceImpl,
                                   AddressBServiceImpl addressBServiceImpl,
                                   AleloCardBServiceImpl aleloCardBService) {
        this.consumerBService = consumerBService;
        this.contactBServiceImpl = contactBServiceImpl;
        this.addressBServiceImpl = addressBServiceImpl;
        this.aleloCardBService = aleloCardBService;
    }

    public ResponseEntity<ApiDTO<PageDTO<ConsumerDTO>>> prepareToRecoverAllCustomers(Integer page, Integer records){
        page = page == null ? 0 : page;
        records = records == null ? 500 : records;
        Pageable pageable = PageRequest.of(page, records, Sort.by("name"));
        try{
            Page<Consumer> consumerPage = this.consumerBService.recoverAllCustomers(pageable);
            List<ConsumerDTO> consumers = new ArrayList<>();
            consumerPage.getContent().forEach((Consumer consumer) -> {
                ConsumerDTO consumerDTO = new ConsumerDTO();
                BeanUtils.copyProperties(consumer, consumerDTO);

                log.info("Retrieving customer cards...");
                List<ConsumerAleloCardDTO> cards = this.aleloCardBService.recoverAllByConsumer(consumer).stream()
                        .map(consumerAleloCard -> new ConsumerAleloCardDTO(consumerAleloCard.getId(), consumerAleloCard.getType(), consumerAleloCard.getNumber(), consumerAleloCard.getBalance()))
                        .collect(Collectors.toList());

                log.info("Retrieving customer address...");
                List<ConsumerAddressDTO> address = this.addressBServiceImpl.recoverAllByConsumer(consumer).stream()
                        .map(cAddress -> new ConsumerAddressDTO(cAddress.getId(), cAddress.getStreet(), cAddress.getNumber(), cAddress.getCity(), cAddress.getCountry(), cAddress.getPortalCode(), cAddress.getCreatedAt()))
                        .collect(Collectors.toList());

                log.info("Retrieving customer contacts...");
                List<ConsumerContactDTO> contacts = this.contactBServiceImpl.recoverAllByConsumer(consumer).stream()
                        .map(cContact -> new ConsumerContactDTO(cContact.getId(), cContact.getType(), cContact.getValue(), cContact.getCreatedAt()))
                        .collect(Collectors.toList());

                consumerDTO.setCards(cards);
                consumerDTO.setAddress(address);
                consumerDTO.setContacts(contacts);
                consumers.add(consumerDTO);
            });

            log.info("Creating object to return...");
            PageDTO<ConsumerDTO> pageDTO = new PageDTO<>(
                    consumers,
                    new LinkDTO(page, consumerPage.getTotalPages()),
                    new MetaDTO(consumerPage.getTotalElements(), consumerPage.getTotalPages())
            );
            return ResponseEntity.ok(new ApiDTO<>(Constants.Codes.CODE_OK, Constants.Success.API_OK, pageDTO));
        }catch (ConsumerRecoverCustomersException crce){
            return ResponseEntity.unprocessableEntity()
                    .body(
                            new ApiDTO<>(Constants.Codes.CODE_NG,
                                    Constants.Errors.BUSINESS_SERROR,
                                    null)
                    );
        }catch (Exception e){
            log.error("Something went wrong in the recovery process", e);
            return new ResponseEntity<>(
                    new ApiDTO<>(Constants.Codes.CODE_NG, Constants.Errors.BUSINESS_SERROR, null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
