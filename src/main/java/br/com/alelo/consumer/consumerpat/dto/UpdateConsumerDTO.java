package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UpdateConsumerDTO implements Serializable {
    private Long id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;
    private List<ContactDTO> contacts;
    private AddressDTO address;
    @JsonIgnore
    private List<Card> cards;
}
