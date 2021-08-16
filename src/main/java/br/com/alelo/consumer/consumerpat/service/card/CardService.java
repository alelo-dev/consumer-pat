package br.com.alelo.consumer.consumerpat.service.card;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.exception.BalanceNotEnoughException;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.Balance;
import br.com.alelo.consumer.consumerpat.model.category.CategoryEnum;
import br.com.alelo.consumer.consumerpat.model.purchase.FoodPurchase;
import br.com.alelo.consumer.consumerpat.model.purchase.FuelPurchase;
import br.com.alelo.consumer.consumerpat.model.purchase.Purchase;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	ExtractRepository extractRepository;
	
	/**
	 * Atualiza o saldo do cartão
	 * 
	 * @param balance
	 */
	public void setBalance(Balance balance) {
		Card card = getCard(balance.getCardNumber(), balance.getCategory());
		card.setBalance(card.getBalance() + balance.getValue());
		saveCard(card);
	}

	
	/**
	 * Obtem cartão de acordo com o número e a categoria.
	 * 
	 * @param cardNumber
	 * @param category
	 * @return Card
	 */
	public Card getCard(String number, Integer category) {
		Card card = cardRepository.findByNumberAndCategoryId(number, category);
		if(card == null) {
			throw new EntityNotFoundException("Cartão não encontrado, favor verificar o número e a categoria do cartão.");
		}
		
		return card;		
	}

	
	/**
	 * Salva cartão
	 * 
	 * @param card
	 */
	public void saveCard(Card card) {
		cardRepository.save(card);
	}
	
	
	/**
	 * Calcula compras e atualiza saldo.
	 * 
	 * @param purchase
	 */
	public void purchase(Purchase purchase) {
		Card card = getCard(purchase.getCardNumber(),purchase.getEstablishmentType());
        
		if (purchase.getEstablishmentType().equals(CategoryEnum.FOOD.getCategory()))        	
			calculationFood(card,purchase);        
		
		if(purchase.getEstablishmentType().equals(CategoryEnum.DRUGSTORE.getCategory()))
        	calculationDrugstore(card,purchase);
		
		if(purchase.getEstablishmentType().equals(CategoryEnum.FUEL.getCategory()))                	
        	calculationFuel(card,purchase);        
		
		saveExtract(purchase);
	}
	
	
	/**
	 * Valida saldo e realiza calculo.
	 * 
	 * @param card
	 * @param value
	 */
	private void validBalanceEnogh(Card card, Double value) {
		if(card.getBalance() - value < 0) {
			throw new BalanceNotEnoughException("Saldo insuficiente");
		}
		card.setBalance(card.getBalance() - value);
	}
	
	
	/**
	 * Salva Extract.
	 * 
	 * @param purchase
	 */
	private void saveExtract(Purchase purchase) {
		Extract extract = Extract.builder()
		        .establishmentName(purchase.getEstablishmentName())
		        .productDescription(purchase.getProductDescription())
		        .dateBuy(new Date())
		        .cardNumber(purchase.getCardNumber())
		        .value(purchase.getValue())
		        .build();
        
        extractRepository.save(extract);
	}
	
	private void calculationFood(Card card, Purchase purchase) {
		 Double cashback = FoodPurchase.purchaseCalculation(purchase.getValue());
         validBalanceEnogh(card, cashback);                       
         saveCard(card);
	}
	
	private void calculationDrugstore(Card card, Purchase purchase) {		    
		validBalanceEnogh(card, purchase.getValue());
    	saveCard(card);
	}
	
	private void calculationFuel(Card card, Purchase purchase) {
		 Double tax  = FuelPurchase.purchaseCalculation(purchase.getValue()); 
         validBalanceEnogh(card, tax);            
         saveCard(card);
	}
}
