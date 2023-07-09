package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ExtractService {

    private final ExtractRepository extractRepository;
    private final EstablishmentRepository establishmentRepository;

    public Extract save(String establishmentName, String productDescription, Card card, Double amount) {
        log.info("Salvar extract:");
        Establishment establishment = establishmentRepository.findByName(establishmentName);

        if (card != null && establishment != null) {
            Extract extract = Extract.builder()
                    .establishment(establishment)
                    .card(card)
                    .productDescription(productDescription)
                    .dateBuy(LocalDate.now())
                    .amount(amount)
                    .build();

            log.info(extract.toString());
            return extractRepository.save(extract);
        }

        return null;
    }
}
