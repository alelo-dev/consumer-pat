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

@Component
@Slf4j
public class ConsumerUpdateBObject {

    private final ConsumerBService consumerBService;
    private final ContactBServiceImpl contactBServiceImpl;
    private final AddressBServiceImpl addressBServiceImpl;
    private final AleloCardBServiceImpl aleloCardBService;

    public ConsumerUpdateBObject(ConsumerBService consumerBService,
                                 ContactBServiceImpl contactBServiceImpl,
                                 AddressBServiceImpl addressBServiceImpl,
                                 AleloCardBServiceImpl aleloCardBService) {
        this.consumerBService = consumerBService;
        this.contactBServiceImpl = contactBServiceImpl;
        this.addressBServiceImpl = addressBServiceImpl;
        this.aleloCardBService = aleloCardBService;
    }

    @Transactional
    public ResponseEntity<ApiDTO<ConsumerDTO>> prepareToUpdateCustomer(ConsumerDTO consumer){
        try{
            List<ConsumerContactDTO> savedContacts = new ArrayList<>();
            List<ConsumerAddressDTO> savedAddress = new ArrayList<>();
            List<ConsumerAleloCardDTO> savedCards = new ArrayList<>();

            log.info("Recovering consumer...");
            Consumer consumerExists = this.consumerBService.recoverByDocumentNumber(consumer.getDocumentNumber());
            if(!Objects.isNull(consumerExists)){
                log.info("Consumer found -> {}", consumerExists.getDocumentNumber());
                Consumer consumerToUpdate = new Consumer();
                BeanUtils.copyProperties(consumer, consumerToUpdate);
                consumerToUpdate.setCreatedAt(consumerExists.getCreatedAt());
                Consumer savedConsumer = this.consumerBService.save(consumerToUpdate);

                consumer.getCards().forEach((ConsumerAleloCardDTO cardDto) -> {
                    ConsumerAleloCard aleloCard = this.aleloCardBService.recoverByCardNumber(cardDto.getNumber());
                    if(Objects.isNull(aleloCard)){
                        aleloCard = new ConsumerAleloCard();
                        aleloCard.setBalance(cardDto.getBalance());
                        aleloCard.setId(null);
                    }
                    aleloCard.setNumber(cardDto.getNumber());
                    aleloCard.setType(cardDto.getType());
                    aleloCard.setConsumer(savedConsumer);
                    aleloCard = this.aleloCardBService.save(aleloCard);
                    savedCards.add(new ConsumerAleloCardDTO(aleloCard.getId(), aleloCard.getType(), aleloCard.getNumber(), aleloCard.getBalance()));
                });

                consumer.getAddress().forEach((ConsumerAddressDTO addressDTO) -> {
                    ConsumerAddress address = this.addressBServiceImpl.recoverById(addressDTO.getId());
                    if(Objects.isNull(address)){
                        address = new ConsumerAddress(addressDTO.getStreet(), addressDTO.getNumber(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPortalCode());
                        address.setCreatedAt(LocalDateTime.now());
                    }else{
                        address.setId(addressDTO.getId());
                        address = new ConsumerAddress(addressDTO.getId(), addressDTO.getStreet(), addressDTO.getNumber(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPortalCode());
                    }
                    address.setConsumer(savedConsumer);
                    address = this.addressBServiceImpl.save(address);
                    savedAddress.add(new ConsumerAddressDTO(address.getId(), address.getStreet(), address.getNumber(), address.getCity(), address.getCountry(), address.getPortalCode(), address.getCreatedAt()));
                });

                consumer.getContacts().forEach((ConsumerContactDTO contactDTO) -> {
                    ConsumerContact contact = this.contactBServiceImpl.recoverById(contactDTO.getId());
                    if(Objects.isNull(contact)){
                        contact = new ConsumerContact(contactDTO.getType(), contactDTO.getValue());
                        contact.setCreatedAt(LocalDateTime.now());
                    }else{
                        contact = new ConsumerContact(contactDTO.getId(), contactDTO.getType(), contactDTO.getValue(), contactDTO.getCreatedAt());
                    }

                    contact.setConsumer(savedConsumer);

                    contact = this.contactBServiceImpl.save(contact);
                    savedContacts.add(new ConsumerContactDTO(contact.getId(), contact.getType(), contact.getValue(), contact.getCreatedAt()));
                });

                ConsumerDTO consumerDTO = new ConsumerDTO(savedConsumer, savedCards, savedContacts, savedAddress);
                return ResponseEntity.ok(new ApiDTO<>(Constants.Codes.CODE_OK, Constants.Success.API_OK, consumerDTO));
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
