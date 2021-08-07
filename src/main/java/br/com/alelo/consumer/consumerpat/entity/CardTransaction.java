package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alelo.consumer.consumerpat.enuns.CardOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_transactions")
public class CardTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cardNumber;
	
	@Enumerated(EnumType.STRING)
	private CardOperationEnum operation;
	private BigDecimal value;
	private BigDecimal balance;
	
	private LocalDateTime createTransaction;
}
