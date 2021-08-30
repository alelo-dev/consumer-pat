package br.com.alelo.consumer.consumerpat.domain.payload;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import lombok.Data;

@Data
public class ConsumerPayload {
	
    private String name;
    private String documentNumber;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private ContactDTO contact;
    private AddressDTO address;
    private Set<CardPayload> cards;

}
