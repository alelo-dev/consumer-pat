package br.com.alelo.consumer.consumerpat.model.purchase;

public class FuelPurchase {
	
	private FuelPurchase() {}
	
	/**
	 * Realiza calculo de acrecimento para compra de cartão nos estabelcimentos de combustiveis.
	 * 
	 * @param value
	 * @return Double
	 */
	public static Double purchaseCalculation(Double value) {
		Double tax  = (value / 100) * 35;
		return (value + tax) ;               
	}
}
