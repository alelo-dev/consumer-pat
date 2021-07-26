package br.com.alelo.consumer.consumerpat.business.object;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 25/07/2021 | 09:49
 */

import br.com.alelo.consumer.consumerpat.business.service.ExtractBService;
import br.com.alelo.consumer.consumerpat.business.service.impl.AleloCardBServiceImpl;
import br.com.alelo.consumer.consumerpat.converter.EstablishmentToAleloType;
import br.com.alelo.consumer.consumerpat.entity.ConsumerAleloCard;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.enums.AleloCardTypeEnum;
import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class ExtractBObject {

    private final ExtractBService extractBService;
    private final AleloCardBServiceImpl aleloCardBService;



    public ExtractBObject(ExtractBService extractBService,
                          AleloCardBServiceImpl aleloCardBService) {
        this.extractBService = extractBService;
        this.aleloCardBService = aleloCardBService;
    }

    public ResponseEntity<ApiDTO<ExtractDTO>> prepareToExtract(Integer establishmentType,
                                                               String establishmentName,
                                                               String cardNumber,
                                                               String productDescription,
                                                               BigDecimal value){
        try{
            ConsumerAleloCard aleloCard = this.aleloCardBService.recoverByCardNumber(cardNumber);
            if(!Objects.isNull(aleloCard)){
                AleloCardTypeEnum aleloCardType = EstablishmentToAleloType.convert(establishmentType);
                BigDecimal cashBack = new BigDecimal((value.longValue() / Constants.Numbers.ONE_HUNDRED) * aleloCardType.getDiscountPercent());
                value = value.subtract(cashBack);
                if(aleloCard.getBalance().compareTo(value) > 0){
                    aleloCard.setBalance(aleloCard.getBalance().subtract(value));
                    this.aleloCardBService.save(aleloCard);
                    Extract extract = new Extract(establishmentType, establishmentName, productDescription, LocalDateTime.now(), cardNumber, value);
                    this.extractBService.save(extract);
                    ExtractDTO extractDTO = new ExtractDTO();
                    BeanUtils.copyProperties(extract, extractDTO);
                    return ResponseEntity
                            .created(new URI(Constants.Links.APP_URL))
                            .body(new ApiDTO<>(Constants.Codes.CODE_OK, Constants.Success.API_OK, extractDTO));
                }else{
                    return ResponseEntity.ok().body(new ApiDTO<>(Constants.Codes.CODE_NG, Constants.Errors.INSUFICIENT_BALANCE, null));
                }
            }else{
                return ResponseEntity.badRequest().body(new ApiDTO<>(Constants.Codes.CODE_NG, Constants.Errors.RECOVER_CARD, null));
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ApiDTO<>(Constants.Codes.CODE_FAIL, Constants.Errors.BUSINESS_SERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
