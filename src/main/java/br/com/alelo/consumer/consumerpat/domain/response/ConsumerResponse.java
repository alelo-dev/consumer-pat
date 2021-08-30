package br.com.alelo.consumer.consumerpat.domain.response;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
    private String documentNumber;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private ContactDTO contact;
    private AddressDTO address;
    private Set<CardResponse> cards;


}
