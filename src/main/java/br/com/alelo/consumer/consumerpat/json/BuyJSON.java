package br.com.alelo.consumer.consumerpat.json;

import lombok.Data;

/**
 * 
 * @author Alexandre Ventecinco
 * @since  15/09/2021
 * 
 * Classe Wrapper para utilização em controller.
 * 
 * @see ConsumerController.buy
 *
 */
@Data
public class BuyJSON {
	
	private int establishmentType ;
	private String establishmentName ;
	private int cardNumber ;
	private String productDescription ;
	private double value ;

}
