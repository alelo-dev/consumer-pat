package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.ContactType;
import lombok.Value;

@Value
public class CreateContactDTO {

    String contact;

    ContactType contactType;

}
