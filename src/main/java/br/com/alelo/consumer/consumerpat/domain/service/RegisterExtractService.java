package br.com.alelo.consumer.consumerpat.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.model.Extract;
import br.com.alelo.consumer.consumerpat.domain.model.dto.PaymentInformation;
import br.com.alelo.consumer.consumerpat.domain.repository.ExtractRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegisterExtractService {
    
    final private ExtractRepository extractRepository;

    public void save (PaymentInformation paymentInformation){
        
        Extract extract = Extract.builder()
            .establishmentName(paymentInformation.getEstablishmentName())
            .productDescription(paymentInformation.getProductDescription())
            .paymentDate(LocalDateTime.now())
            .cardNumber(paymentInformation.getCardNumber())
            .value(paymentInformation.getValue())
            .build();

        extractRepository.save(extract);
    }
}
