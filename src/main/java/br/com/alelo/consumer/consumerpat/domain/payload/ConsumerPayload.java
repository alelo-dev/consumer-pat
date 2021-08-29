package br.com.alelo.consumer.consumerpat.domain.payload;

import java.util.Date;
import java.util.Set;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import lombok.Data;

@Data
public class ConsumerPayload {
	
    private String name;
    private int documentNumber;
    private Date birthDate;
    private ContactDTO contact;
    private AddressDTO address;
    private Set<CardPayload> cards;

}
