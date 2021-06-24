package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.StatementAccount;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.parameter.TransactionParameter;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.StatementAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TransactionService.class)
class TransactionServiceTest {

	@Autowired
	TransactionService transactionService;

	@MockBean
	ConsumerService consumerService;

	@MockBean
	StatementAccountRepository statementAccountRepository;

	@MockBean
	CardRepository cardRepository;

	@Test
	void buyCardFoodIsOK() {
		Card card = new Card();
		card.setBalance(new BigDecimal("100"));
		card.setNumber("1234567887654321");
		card.setType(CardType.FOOD);

		TransactionParameter parameter = new TransactionParameter();
		parameter.setConsumerId(1);
		parameter.setCardNumber(card.getNumber());
		parameter.setTransactionAmount(new BigDecimal("50"));
		parameter.setCardType(CardType.FOOD);

		when(consumerService.verifyExistsConsumerById(parameter.getConsumerId())).thenReturn(true);
		when(consumerService.getCard(parameter.getCardNumber(), parameter.getConsumerId())).thenReturn(card);

		transactionService.buy(parameter);

		verify(cardRepository, times(1)).save(card);
		verify(statementAccountRepository, times(1)).save(any(StatementAccount.class));
	}

	@Test
	void buyCardDrugstoreIsOK() {
		Card card = new Card();
		card.setBalance(new BigDecimal("100"));
		card.setNumber("1234567887654321");
		card.setType(CardType.DRUGSTORE);

		TransactionParameter parameter = new TransactionParameter();
		parameter.setConsumerId(1);
		parameter.setCardNumber(card.getNumber());
		parameter.setTransactionAmount(new BigDecimal("50"));
		parameter.setCardType(CardType.DRUGSTORE);

		when(consumerService.verifyExistsConsumerById(parameter.getConsumerId())).thenReturn(true);
		when(consumerService.getCard(parameter.getCardNumber(), parameter.getConsumerId())).thenReturn(card);

		transactionService.buy(parameter);

		verify(cardRepository, times(1)).save(card);
		verify(statementAccountRepository, times(1)).save(any(StatementAccount.class));
	}

	@Test
	void buyCardFuelIsOK() {
		Card card = new Card();
		card.setBalance(new BigDecimal("100"));
		card.setNumber("1234567887654321");
		card.setType(CardType.FUEL);

		TransactionParameter parameter = new TransactionParameter();
		parameter.setConsumerId(1);
		parameter.setCardNumber(card.getNumber());
		parameter.setTransactionAmount(new BigDecimal("50"));
		parameter.setCardType(CardType.FUEL);

		when(consumerService.verifyExistsConsumerById(parameter.getConsumerId())).thenReturn(true);
		when(consumerService.getCard(parameter.getCardNumber(), parameter.getConsumerId())).thenReturn(card);

		transactionService.buy(parameter);

		verify(cardRepository, times(1)).save(card);
		verify(statementAccountRepository, times(1)).save(any(StatementAccount.class));
	}

	@Test
	void buyWithInsufficientFunds() {
		TransactionParameter parameter = new TransactionParameter();
		parameter.setConsumerId(1);
		parameter.setCardNumber("1234567887654321");
		parameter.setTransactionAmount(new BigDecimal("100"));
		parameter.setCardType(CardType.FUEL);

		Card card = new Card();
		card.setBalance(new BigDecimal("100"));
		card.setNumber(parameter.getCardNumber());
		card.setType(parameter.getCardType());

		when(consumerService.verifyExistsConsumerById(parameter.getConsumerId())).thenReturn(true);
		when(consumerService.getCard(parameter.getCardNumber(), parameter.getConsumerId())).thenReturn(card);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionService.buy(parameter));
		assertEquals("Saldo do cartão menor que o valor da transação", exception.getMessage());
	}
}