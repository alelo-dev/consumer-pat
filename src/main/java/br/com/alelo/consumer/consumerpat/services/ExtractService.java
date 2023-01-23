package br.com.alelo.consumer.consumerpat.services;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExtractService {

    private final @NonNull
    ExtractRepository extractRepository;

    public Extract save(int establishmentType, String establishmentName, String productDescription, Date date,
                        Long cardNumber, Double value, Double cashback, Double taxa) {

        Extract extract = Extract.builder()
                .establishmentNameId(establishmentType)
                .establishmentName(establishmentName)
                .productDescription(productDescription)
                .dateBuy(date)
                .cardNumber(cardNumber)
                .value(value)
                .cashback(cashback)
                .taxa(taxa)
                .build();

        return extractRepository.save(extract);
    }
}
