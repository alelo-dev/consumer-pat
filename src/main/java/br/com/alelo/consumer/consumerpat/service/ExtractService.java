package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ExtractService {

    private final ExtractRepository extractRepository;
    private final EstablishmentRepository establishmentRepository;

    public Extract save(String establishmentName, String productDescription, Card card, Double amount) {
        Establishment establishment = establishmentRepository.findByName(establishmentName);

        if (card != null && establishment != null) {
            Extract extract = Extract.builder()
                    .establishment(establishment)
                    .card(card)
                    .productDescription(productDescription)
                    .dateBuy(LocalDate.now())
                    .amount(amount)
                    .build();

            return extractRepository.save(extract);
        }

        return null;
    }
}
