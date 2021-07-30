package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExtractService {

    private final ExtractRepository extractRepository;

    @Transactional
    public Extract create(TransactionDTO dto, Card card, BigDecimal value) {
        Extract entity = Extract.builder()
                .card(card)
                .dateBuy(LocalDateTime.now())
                .establishmentName(dto.getEstablishmentName())
                .productDescription(dto.getProductDescription())
                .value(value)
                .build();
        return extractRepository.save(entity);
    }
}
