package br.com.alelo.consumer.consumerpat.processor;

import br.com.alelo.consumer.consumerpat.dto.SaleData;

public class DrugStoreSaleProcessor extends BaseSalesProcessor{

	private double value; 
	
	private double currentBalance;

	public DrugStoreSaleProcessor(SaleData saleData) { 
		this.value = saleData.getSaleRequestDTO().getValue(); 
		this.currentBalance = saleData.getCardConsumer().getBalance().doubleValue();
	} 

	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		
		super.validateBalance(value, currentBalance);

		return this.value;
	}

}
