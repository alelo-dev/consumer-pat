package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ExtractServiceImpl implements ExtractService {

    private ExtractRepository repository;

    @Override
    public Extract save(final Card card, final BuyDTO dto, final LocalDate date) {

        try {
            final Extract buildExtract = Extract.builder()
                    .cardNumber(card.getNumber())
                    .establishmentType(card.getCardType().getType())
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
