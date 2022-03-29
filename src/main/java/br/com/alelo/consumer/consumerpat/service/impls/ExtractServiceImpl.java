package br.com.alelo.consumer.consumerpat.service.impls;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.interfaces.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExtractServiceImpl implements ExtractService {
    @Autowired
    ExtractRepository extractRepository;

    public void insert(BuyDTO buy) {
        Extract extract = Extract.builder()
                .establishmentName(buy.getEstablishmentName())
                .productDescription(buy.getProductDescription())
                .dateBuy(LocalDateTime.now())
                .cardNumber(buy.getCardNumber())
                .value(buy.getValue())
                .build();

        extractRepository.save(extract);
    }
}
