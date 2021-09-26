package br.com.alelo.consumer.consumerpat.processor;

import br.com.alelo.consumer.consumerpat.dto.SaleData;

public class FoodSaleProcessor extends BaseSalesProcessor{
	

	private double value; 

	private double tax;
	
	private double currentBalance;
	
	public FoodSaleProcessor(SaleData saleData, double tax) { 
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
