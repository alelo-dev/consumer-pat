package br.com.alelo.consumer.consumerpat.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Extract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String establishmentName;
	private String productDescription;
	private LocalDateTime dateBuy;
	private Long cardNumber;
	private BigDecimal value;

	public Extract(String establishmentName, String productDescription, LocalDateTime dateBuy, Long cardNumber,
			BigDecimal value) {
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}
}
