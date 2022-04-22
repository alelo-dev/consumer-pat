package br.com.alelo.consumer.consumerpat.disassembler;

import br.com.alelo.consumer.consumerpat.dto.input.RequestInputDTO;
import br.com.alelo.consumer.consumerpat.entity.*;
import org.springframework.stereotype.Component;

@Component
public class RequestInputDTODisassembler {

    public Request toDomainObject(RequestInputDTO requestInputDTO) {

        Request request = new Request();

        Establishment establishment = new Establishment();
        establishment.setId(requestInputDTO.getEstablishmentId());
        request.setEstablishment(establishment);

        Card card = new Card();
        card.setId(requestInputDTO.getCardId());
        request.setCard(card);

        Consumer consumer = new Consumer();
        consumer.setId(requestInputDTO.getConsumerId());
        request.setConsumer(consumer);

        Product product = new Product();
        product.setId(requestInputDTO.getProductId());
        request.setProduct(product);

        return request;
    }
}
