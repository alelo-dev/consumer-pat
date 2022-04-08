package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ConsumerDTO {

    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    private ConsumerContactsDTO contacts;
    private AddressDTO address;

    private CardDTO foodCard;
    private CardDTO fuelCard;
    private CardDTO drugstoreCard;

    public static ConsumerDTO from(Consumer consumer) {
        var dto = new ConsumerDTO();
        dto.id = consumer.getId();
        dto.name = consumer.getName();
        dto.documentNumber = consumer.getDocumentNumber();
        dto.birthDate = consumer.getBirthDate();
        if (consumer.getContacts() != null) {
            dto.contacts = ConsumerContactsDTO.from(consumer.getContacts());
        }
        if (consumer.getAddress() != null) {
            dto.address = AddressDTO.from(consumer.getAddress());
        }
        if (consumer.getFoodCard() != null) {
            dto.foodCard = CardDTO.from(consumer.getFoodCard());
        }
        if (consumer.getDrugstoreCard() != null) {
            dto.drugstoreCard = CardDTO.from(consumer.getDrugstoreCard());
        }
        if (consumer.getFuelCard() != null) {
            dto.fuelCard = CardDTO.from(consumer.getFuelCard());
        }
        return dto;
    }
}
