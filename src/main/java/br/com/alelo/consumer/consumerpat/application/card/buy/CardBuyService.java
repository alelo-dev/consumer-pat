package br.com.alelo.consumer.consumerpat.application.card.buy;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasLength;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.application.card.buy.request.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.response.TransactionResponse;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ExtractRepository;

@Service
public class CardBuyService {

	@Autowired
	private ExtractRepository extractRepository;

	@Autowired
	private CardRepository cardRepository;

	public TransactionResponse buy(CardBuyRequest request) {
		validated(request);
		Card card = getCardByNumber(request.getCardNumber());
		BigDecimal debitValue = card.debit(request.getValue());
		cardRepository.save(card);
		Extract extract = updateExtract(request, debitValue);
		return getTransactionInfo(extract);
	}

	private TransactionResponse getTransactionInfo(Extract extract) {

		TransactionResponse response = new TransactionResponse();
		BeanUtils.copyProperties(extract, response);

		return response;
	}

	private void validated(CardBuyRequest request) {
		ErrorMessageDto errors = new ErrorMessageDto();
		if (isNull(request.getCardNumber())) {
			errors.addAdditionalMessages("card number not found");
		}

		if (!hasLength(request.getEstablishmentName())) {
			errors.addAdditionalMessages("Establishment Name not found");
		}

		if (!hasLength(request.getProductDescription())) {
			errors.addAdditionalMessages("Product Description Name not found");
		}

		if (BigDecimal.ZERO.equals(request.getValue())) {
			errors.addAdditionalMessages("value not valid");
		}

		if (errors.containsErrors()) {
			throw new BusinessException("invalid buy request", errors);
		}
	}

	private Card getCardByNumber(Long cardNumber) {
		Optional<Card> findById = cardRepository.findById(cardNumber);
		if (findById.isEmpty()) {
			throw new BusinessException("card not found");
		}

		return findById.get();
	}

	private Extract updateExtract(CardBuyRequest request, BigDecimal value) {
		Extract extract = new Extract(request.getEstablishmentName(), request.getProductDescription(),
				LocalDateTime.now(), request.getCardNumber(), value);
		return extractRepository.save(extract);
	}

}
