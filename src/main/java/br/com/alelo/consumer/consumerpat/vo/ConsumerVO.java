package br.com.alelo.consumer.consumerpat.vo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerVO {

	private Integer id;
	private String name;
	private int documentNumber;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

	// contacts
	private int mobilePhoneNumber;
	private int residencePhoneNumber;
	private int phoneNumber;
	private String email;

	// Address
	private String street;
	private int number;
	private String city;
	private String country;
	private int portalCode;

	// cards
	private int foodCardNumber;
	private double foodCardBalance;
	private int fuelCardNumber;
	private double fuelCardBalance;
	private int drugstoreNumber;
	private double drugstoreCardBalance;

}
