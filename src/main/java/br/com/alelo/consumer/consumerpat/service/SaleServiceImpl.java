package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.constants.EnumCardType;
import br.com.alelo.consumer.consumerpat.constants.EnumParameterTax;
import br.com.alelo.consumer.consumerpat.dto.SaleData;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.Parameter;
import br.com.alelo.consumer.consumerpat.processor.DrugStoreSaleProcessor;
import br.com.alelo.consumer.consumerpat.processor.FoodSaleProcessor;
import br.com.alelo.consumer.consumerpat.processor.FuelSaleProcessor;
import br.com.alelo.consumer.consumerpat.processor.TaxSaleCalculator;
import br.com.alelo.consumer.consumerpat.repository.CardConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.repository.ParameterRepository;

@Service
public class SaleServiceImpl implements ISaleService{
	
	@Autowired
	CardConsumerRepository cardConsumerRepository;
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	@Autowired
	ExtractRepository extractRepository;
	
	@Autowired
	TaxSaleCalculator taxSaleCalculator;
	
	@Autowired
	ParameterRepository parameterRepository;

	@Override
	public void sell(SaleData saleData) {
		// TODO Auto-generated method stub
		
		double valueWithTax = 0;
		
		if(saleData.getCardConsumer().getCardType().getId().equals(EnumCardType.DRUGSTORE_CARD.getValueName())) {
			valueWithTax=	taxSaleCalculator.calcular(new DrugStoreSaleProcessor(saleData));
		}else if(saleData.getCardConsumer().getCardType().getId().equals(EnumCardType.FOOD_CARD.getValueName())) {
			Parameter parameterFood = parameterRepository.getParameterByName(EnumParameterTax.TAX_FOOD.getValueName());
			valueWithTax=taxSaleCalculator.calcular(new FoodSaleProcessor(saleData,
					Double.parseDouble(parameterFood.getValueParameter())));
		}else if(saleData.getCardConsumer().getCardType().getId().equals(EnumCardType.FUEL_CARD.getValueName())) {
			Parameter parameterFood = parameterRepository.getParameterByName(EnumParameterTax.TAX_FUEL.getValueName());
			valueWithTax=taxSaleCalculator.calcular(new FuelSaleProcessor(saleData, 
					Double.parseDouble(parameterFood.getValueParameter())));
		}

		Extract extract = Extract.builder().establishment(saleData.getEstablishment()).cardConsumer(saleData.getCardConsumer()).purchaseValue(valueWithTax).build();
		
		extractRepository.save(extract);
	}

}
