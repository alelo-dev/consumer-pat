package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.alelo.consumer.consumerpat.entity.CardTransaction;
import br.com.alelo.consumer.consumerpat.enuns.CardOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionDTO {

	private Long id;
	private String cardNumber;
	private CardOperationEnum operation;
	private BigDecimal value;
	private BigDecimal balance;
	private LocalDateTime createTransaction;
	
	public static CardTransactionDTO to(CardTransaction cardTransaction) {
		return new CardTransactionDTO(
					cardTransaction.getId(),
					cardTransaction.getCardNumber(),
					cardTransaction.getOperation(),
					cardTransaction.getValue(),
					cardTransaction.getBalance(),
					cardTransaction.getCreateTransaction()
				);
	}
}
