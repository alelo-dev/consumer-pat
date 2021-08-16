package br.com.alelo.consumer.consumerpat.model.purchase;

public class FoodPurchase {

	private FoodPurchase() {}
	
	/**
	 * Realiza desconto para compra nos estabelcimentos de alimentação.
	 * 
	 * @param value
	 * @return Double
	 */
	public static Double purchaseCalculation(Double value) {
		 Double cashback  = (value / 100) * 10;           
		 return (value - cashback) ;               
	}
}
