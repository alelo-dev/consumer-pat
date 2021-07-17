package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.IExtractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExtractService implements IExtractService {

    private final IExtractRepository repository;

    @Override
    public Extract save(final Card card, final RequestBuyDTO dto, final LocalDate date) {

        try {
            final Extract buildExtract = Extract.builder()
                    .cardNumber(card.getNumber())
                    .establishmentType(card.getCardType().getCode())
                    .establishmentName(dto.getEstablishmentName())
                    .productDescription(dto.getProductDescription())
                    .dateBuy(date)
                    .value(dto.getNewValue())
                    .build();
            return repository.save(buildExtract);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
