package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.model.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.model.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ExtractServiceImpl implements ExtractService {

    private ExtractRepository extractRepository;

    private CardRepository cardRepository;

    @Override
    public Page<Extract> findAll(ExtractFilterVO filters, Pageable pageable) {
        return extractRepository.findAllByFilters(filters, pageable);
    }

    @Override
    @Transactional
    public Extract save(NewExtractFormVO form) {
        Card card = cardRepository.findByCardNumber(form.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found!"));

        validateExtract(card, form);
        adjustValueByEstablishmentType(form);
        updateCardBalance(form, card);
        Extract extract = buildExtract(card, form);

        return extractRepository.save(extract);
    }

    private void adjustValueByEstablishmentType(NewExtractFormVO form) {
        if (form.getValue().compareTo(BigDecimal.ZERO) > 0) {
            form.setValue(form.getValue()
                .multiply(EstablishmentType.fromCode(form.getEstablishmentType())
                    .getMultiplyFactor())
            );
        }
    }

    private void validateExtract(Card card, NewExtractFormVO form) {
        if (isEstablishmentAllowed(card, form)) {
            throw new BusinessException("Establishment not allowed for the informed card!");
        }
        if (cardHasEnoughBalance(card, form)) {
            throw new BusinessException("The card does not have enough balance!");
        }
    }

    private boolean isEstablishmentAllowed(Card card, NewExtractFormVO form) {
        return card.getType().getValue().compareTo(form.getEstablishmentType()) != 0;
    }

    private boolean cardHasEnoughBalance(Card card, NewExtractFormVO form) {
        return card.getBalance().compareTo(form.getValue()) < 0;
    }

    private Extract buildExtract(Card card, NewExtractFormVO form) {
        return new Extract(null, form.getEstablishmentId(), form.getEstablishmentName(), form.getProductDescription(),
                LocalDateTime.now(), form.getValue(), card, card.getConsumer());
    }

    private void updateCardBalance(NewExtractFormVO form, Card card) {
        card.setBalance(card.getBalance().subtract(form.getValue()));
        cardRepository.save(card);
    }
}
