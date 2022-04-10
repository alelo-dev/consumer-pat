package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumerDto implements Serializable {

	private static final long serialVersionUID = -209563401312314046L;

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

	private List<ContactDto> contacts;

	private List<AddressDto> addresses;

	private List<CardDto> cards;
}
