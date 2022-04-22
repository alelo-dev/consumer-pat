package br.com.alelo.consumer.consumerpat.assembler;

import br.com.alelo.consumer.consumerpat.dto.ConsumerResumeDTO;
import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExtractDTOAssembler {

    public ExtractDTO toModel(Extract extract) {
        ExtractDTO extractDTO = new ExtractDTO();
        extractDTO.setId(extract.getId());
        extractDTO.setDate(extract.getDate());
        extractDTO.setEstablishment(extract.getEstablishment());

        extractDTO.setCard(extract.getCard());
        extractDTO.setProduct(extract.getProduct());
        extractDTO.setAmount(extract.getAmount());

        ConsumerResumeDTO consumerResumeDTO = new ConsumerResumeDTO();
        consumerResumeDTO.setId(extract.getConsumer().getId());
        consumerResumeDTO.setName(extract.getConsumer().getName());

        extractDTO.setConsumer(consumerResumeDTO);

        return extractDTO;

    }

    public List<ExtractDTO> toCollectionModel(List<Extract> extracts) {
        return extracts.stream()
                .map(extract -> toModel(extract))
                .collect(Collectors.toList());

    }
}
