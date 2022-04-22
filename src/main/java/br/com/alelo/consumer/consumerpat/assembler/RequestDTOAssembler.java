package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.ConsumerResumeDTO;
import br.com.alelo.consumer.consumerpat.dto.RequestDTO;
import br.com.alelo.consumer.consumerpat.entity.Request;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestDTOAssembler {

    public RequestDTO toModel(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setEstablishment(request.getEstablishment());
        requestDTO.setCard(request.getCard());
        requestDTO.setProduct(request.getProduct());

        ConsumerResumeDTO consumerResumeDTO = new ConsumerResumeDTO();
        consumerResumeDTO.setId(request.getConsumer().getId());
        consumerResumeDTO.setName(request.getConsumer().getName());
        requestDTO.setConsumer(consumerResumeDTO);

        return requestDTO;

    }

    public List<RequestDTO> toCollectionModel(List<Request> requests) {
        return requests.stream()
                .map(request -> toModel(request))
                .collect(Collectors.toList());

    }
}
