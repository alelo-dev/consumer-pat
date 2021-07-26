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
import br.com.alelo.consumer.consumerpat.entity.ConsumerAddress;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.entity.ConsumerContact;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverNotFoundException;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerAddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerContactDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ConsumerCreationBObject {

    private final ConsumerBService consumerBService;
    private final ContactBServiceImpl contactBServiceImpl;
    private final AddressBServiceImpl addressBServiceImpl;
    private final AleloCardBServiceImpl aleloCardBService;

    public ConsumerCreationBObject(ConsumerBService consumerBService,
                                   ContactBServiceImpl contactBServiceImpl,
                                   AddressBServiceImpl addressBServiceImpl,
                                   AleloCardBServiceImpl aleloCardBService) {
        this.consumerBService = consumerBService;
        this.contactBServiceImpl = contactBServiceImpl;
        this.addressBServiceImpl = addressBServiceImpl;
        this.aleloCardBService = aleloCardBService;
    }

    @Transactional
    public ResponseEntity<ApiDTO<ConsumerDTO>> prepareToSaveCustomer(ConsumerDTO consumerDTO){
        try{
            Consumer consumerExists = this.consumerBService.recoverByDocumentNumber(consumerDTO.getDocumentNumber());
            List<ConsumerContactDTO> savedContacts = new ArrayList<>();
            List<ConsumerAddressDTO> savedAddress = new ArrayList<>();
            List<ConsumerAleloCardDTO> savedCards = new ArrayList<>();
            if(Objects.isNull(consumerExists)){
                Consumer consumer = new Consumer();
                BeanUtils.copyProperties(consumerDTO, consumer);
                consumer.setCreatedAt(LocalDateTime.now());
                Consumer consumerSaved = this.consumerBService.save(consumer);

                consumerDTO.getContacts().forEach((ConsumerContactDTO contactDTO) -> {
                    ConsumerContact consumerContact = new ConsumerContact(contactDTO.getType(), contactDTO.getValue());
                    consumerContact.setConsumer(consumerSaved);
                    consumerContact = this.contactBServiceImpl.save(consumerContact);
                    savedContacts.add(new ConsumerContactDTO(consumerContact.getId(), consumerContact.getType(), consumerContact.getValue(), consumerContact.getCreatedAt()));
                });

                consumerDTO.getAddress().forEach((ConsumerAddressDTO address) -> {
                    ConsumerAddress consumerAddress = new ConsumerAddress(address.getStreet(), address.getNumber(), address.getCity(), address.getCountry(), address.getPortalCode());
                    consumerAddress.setConsumer(consumerSaved);
                    consumerAddress.setCreatedAt(LocalDateTime.now());
                    consumerAddress = this.addressBServiceImpl.save(consumerAddress);
                    savedAddress.add(new ConsumerAddressDTO(consumerAddress.getId(), consumerAddress.getStreet(), consumerAddress.getNumber(), consumerAddress.getCity(), consumerAddress.getCountry(), consumerAddress.getPortalCode(), consumerAddress.getCreatedAt()));
                });

                consumerDTO.getCards().forEach((ConsumerAleloCardDTO card) -> {
                    ConsumerAleloCard aleloCard = new ConsumerAleloCard(card.getType(), card.getNumber(), card.getBalance());
                    aleloCard.setConsumer(consumerSaved);
                    aleloCard.setCreatedAt(LocalDateTime.now());
                    aleloCard = this.aleloCardBService.save(aleloCard);
                    savedCards.add(new ConsumerAleloCardDTO(aleloCard.getId(), aleloCard.getType(), aleloCard.getNumber(), aleloCard.getBalance()));
                });
                consumerDTO.getCards().stream()
                        .map(aleloCard -> new ConsumerAleloCard(aleloCard.getType(), aleloCard.getNumber(), aleloCard.getBalance()))
                        .collect(Collectors.toList());
                consumer.setCreatedAt(LocalDateTime.now());


                ConsumerDTO savedConsumer = new ConsumerDTO(consumerSaved, savedCards, savedContacts, savedAddress);
                return ResponseEntity.ok(new ApiDTO<>(Constants.Codes.CODE_OK, Constants.Success.API_OK, savedConsumer));
            }else{
                return ResponseEntity.unprocessableEntity()
                        .body(
                                new ApiDTO<>(Constants.Codes.CODE_NG,
                                        Constants.Errors.CUSTOMER_ALREADY_EXISTS,
                                        null)
                        );
            }

        }catch (ConsumerRecoverNotFoundException | ConsumerRecoverCustomersException crnfe){
            return ResponseEntity.unprocessableEntity()
                    .body(
                            new ApiDTO<>(Constants.Codes.CODE_NG,
                                    Constants.Errors.BUSINESS_SERROR,
                                    null)
                    );
        }
    }

}
