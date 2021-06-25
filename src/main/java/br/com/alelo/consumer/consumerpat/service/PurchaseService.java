package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.PurchaseDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.CalculationUtil;

@Service
public class PurchaseService extends BaseService {

	@Autowired
    protected ExtractRepository extractRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Extract buy(PurchaseDTO purchaseDTO) throws BusinessException {
    	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
    	
    	EstablishmentTypeEnum establishmentType = purchaseDTO.getEstablishmentType();
    	String establishmentName = purchaseDTO.getEstablishmentName();
    	int cardNumber = purchaseDTO.getCardNumber();
    	String productDescription = purchaseDTO.getProductDescription();
    	double value = purchaseDTO.getValue();

    	boolean buy = false;
    	if (value >= 0) {
    		value = CalculationUtil.calculateTotalValue(value, establishmentType.getExtraValuePercentage());
    		
	        if (establishmentType == EstablishmentTypeEnum.FOOD) {
	            buy = addFoodCardBalance(cardNumber, -value);
	        } else if (establishmentType == EstablishmentTypeEnum.DRUGSTORE) {
	        	buy = addDrugstoreCardBalance(cardNumber, -value);
	        } else if (establishmentType == EstablishmentTypeEnum.FUEL) {
	        	buy = addFuelCardBalance(cardNumber, -value);
	        }
        }

        if (buy) {
	        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
	        return extractRepository.save(extract);
        } else {
        	throw new BusinessException("Consumer not found.");
        }
    }
}
