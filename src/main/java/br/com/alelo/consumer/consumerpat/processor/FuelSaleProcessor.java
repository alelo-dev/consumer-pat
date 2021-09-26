package br.com.alelo.consumer.consumerpat.processor;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alelo.consumer.consumerpat.dto.SaleData;
import br.com.alelo.consumer.consumerpat.repository.ParameterRepository;


public class FuelSaleProcessor extends BaseSalesProcessor{
	
	@Autowired
	ParameterRepository parameterRepository;

	private double value; 
	
	private double tax;

	private double currentBalance;
	
	public FuelSaleProcessor(SaleData saleData, double tax) { 
		this.value = saleData.getSaleRequestDTO().getValue(); 
		this.tax = tax;
		this.currentBalance = saleData.getCardConsumer().getBalance().doubleValue();
	} 

	
	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		Double cashback  = (value / 100) * tax;
        value = value - cashback;
        
        super.validateBalance(value, currentBalance);
        
        
        return value;
	}

}
