package br.com.alelo.consumer.consumerpat.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.model.entity.Addres;
import br.com.alelo.consumer.consumerpat.model.entity.Contact;
import lombok.Data;

@Data
public class ConsumerDTO {
	
    private String name;
	
    private Integer documentNumber;
	
    private LocalDateTime birthDate;
	
	private List<Contact> contacts = new ArrayList<>();
	
	 private List<Addres> adress = new ArrayList<>();

}
