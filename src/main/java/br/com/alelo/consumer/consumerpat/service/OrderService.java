package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.OrderDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.ExtractOrm;
import br.com.alelo.consumer.consumerpat.entity.transaction.Debit;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CardRepository cardRepository;
    private final ExtractRepository extractRepository;

    public OrderService(CardRepository cardRepository, ExtractRepository extractRepository) {
        this.cardRepository = cardRepository;
        this.extractRepository = extractRepository;
    }

    public UpdateActionResponse createNew(OrderDTO orderDto) {
        var found = cardRepository.findById(orderDto.getCardNumber());
        if (found.isEmpty()) {
            return UpdateActionResponse.NOT_FOUND;
        }

        var card = found.get();
        card.add(new Debit(orderDto.getValue(), card.getType()));

        cardRepository.save(card);
        addExtract(orderDto);
        return UpdateActionResponse.UPDATED;
    }

    private void addExtract(OrderDTO orderDto) {
        var extract = new ExtractOrm(
                orderDto.getEstablishmentName(),
                orderDto.getProductDescription(),
                orderDto.getCardNumber(),
                orderDto.getValue()
        );
        extractRepository.save(extract);
    }
}
