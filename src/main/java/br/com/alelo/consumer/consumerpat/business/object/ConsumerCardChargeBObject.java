package br.com.alelo.consumer.consumerpat.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 08:24
 */

import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;

@Component
@Slf4j
public class ConsumerCardChargeBObject {

    private final AleloCardBServiceImpl aleloCardBService;

    public ConsumerCardChargeBObject(AleloCardBServiceImpl aleloCardBService) {
        this.aleloCardBService = aleloCardBService;
    }

    @Transactional
    public ResponseEntity<ApiDTO<ConsumerAleloCardDTO>> prepareToChargeCard(String cardNumber, BigDecimal value){
        try{
            ConsumerAleloCard aleloCard = this.aleloCardBService.recoverByCardNumber(cardNumber);
            if(!Objects.isNull(aleloCard)){
                aleloCard.setBalance(aleloCard.getBalance().add(value));
                this.aleloCardBService.save(aleloCard);
                ConsumerAleloCardDTO aleloCardDTO = new ConsumerAleloCardDTO(aleloCard.getId(), aleloCard.getType(), aleloCard.getNumber(), aleloCard.getBalance());
                return ResponseEntity.ok()
                        .body(
                                new ApiDTO<>(Constants.Codes.CODE_OK,
                                        Constants.Success.API_OK,
                                        aleloCardDTO)
                        );
            }else{
                return ResponseEntity.unprocessableEntity()
                        .body(
                                new ApiDTO<>(Constants.Codes.CODE_NG,
                                        Constants.Errors.RECOVER_CARD_CONSUMER,
                                        null)
                        );
            }
        }catch (Exception e){
            return new ResponseEntity(new ApiDTO<>(Constants.Codes.CODE_NG,
                    Constants.Errors.BUSINESS_SERROR,
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
