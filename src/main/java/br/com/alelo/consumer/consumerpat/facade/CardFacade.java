package br.com.alelo.consumer.consumerpat.facade;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.controller.dto.BueConfirmationDto;
import br.com.alelo.consumer.consumerpat.controller.dto.BueDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardUpdateDto;
import br.com.alelo.consumer.consumerpat.facade.converter.CardConverter;
import br.com.alelo.consumer.consumerpat.facade.converter.TransactionConverter;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardFacade {
    
    private final CardService service;
    
    public BueConfirmationDto bue(final BueDto bue) {
        var transaction = service.bue(TransactionConverter.toEntity(bue));
        
        return BueConfirmationDto.builder().cardNumber(transaction.getCard().getNumber())
                .confirmationCode(transaction.getConfirmationCode()).date(transaction.getDateBuy())
                .establishmentName(transaction.getEstablishmentName())
                .establishmentType(transaction.getEstablishmentType().name())
                .productDescription(transaction.getProductDescription()).value(transaction.getValue()).build();
    }
    
    public void save(final CardSaveDto card) {
        var dto = CardDto.builder().balance(card.getBalance()).consumerId(card.getConsumerId()).number(card.getNumber())
                .type(card.getType()).build();
        service.save(CardConverter.toEntity(dto));
    }
    
    public void update(final CardUpdateDto card) {
        var dto = CardDto.builder().consumerId(card.getConsumerId()).id(card.getId()).number(card.getNumber())
                .type(card.getType()).build();
        service.update(CardConverter.toEntity(dto));
    }
    
}
