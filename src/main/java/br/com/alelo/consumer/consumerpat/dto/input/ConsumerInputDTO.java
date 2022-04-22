package br.com.alelo.consumer.consumerpat.dto.input;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class ConsumerInputDTO {

    private String name;

    private Integer documentNumber;

    private OffsetDateTime birthDate;

    private AdressInputDTO address;

    private List<Contact> contacts;
}
