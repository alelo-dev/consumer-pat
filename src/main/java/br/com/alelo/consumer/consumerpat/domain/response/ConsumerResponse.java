package br.com.alelo.consumer.consumerpat.domain.response;

import java.util.Date;
import java.util.Set;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerResponse {
	
    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;
    private ContactDTO contact;
    private AddressDTO address;
    private Set<CardResponse> cards;


}
