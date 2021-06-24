package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.model.Extract;
import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import br.com.alelo.consumer.consumerpat.dto.BuyItemRequestDto;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExtractServiceImpl implements ExtractService {

    private final ExtractRepository extractRepository;

    public void saveExtract(BuyItemRequestDto buyItemRequestDto){
        Extract extract = new Extract(buyItemRequestDto.getEstablishmentName(),
                buyItemRequestDto.getProductDescription(), LocalDate.now(),
                buyItemRequestDto.getCardNumber(), buyItemRequestDto.getValue());
        extractRepository.save(extract);
    }
}
