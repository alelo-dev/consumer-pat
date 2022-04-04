package br.com.alelo.consumer.consumerpat.controller;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.PurchaseDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.response.StandardResponse;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.services.EstablishmentService;
import br.com.alelo.consumer.consumerpat.services.PurchaseService;
import br.com.alelo.consumer.consumerpat.util.ReceivedParameterValidator;

@RestController 
@RequestMapping(value="/purchase", produces="application/json")
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
 
    @Autowired
    CardService cardService;
    
    @Autowired
    EstablishmentService establishmentService;
 

    @PostMapping(value = "/buy")
    public ResponseEntity< StandardResponse<Purchase> > buy( @Valid @RequestBody PurchaseDto purchaseDto, 
    		                                                                     BindingResult bindingResult ){

    	StandardResponse<Purchase> standardResponse = new StandardResponse<Purchase>();


		//------
		
		boolean validationResultContainsErrors = ReceivedParameterValidator.checkValidationResultBindingResult( bindingResult, standardResponse );
		if( validationResultContainsErrors ){
		
		return ResponseEntity.badRequest().body( standardResponse );
		
		}

		//------ 
		
    	Card card = cardService.getCardByNumber( purchaseDto.getCardNumber() );
        
    	if( card == null ) {

    		standardResponse.getErrors().add("There is no card with the number: " +purchaseDto.getCardNumber() );
    		
    		return ResponseEntity.badRequest().body( standardResponse );
    	}    	

		//------ 
    	
    	Optional<Establishment> optEestablishment = establishmentService.getEstablishmentById( purchaseDto.getEstablishmentId() );
 
    	if( !optEestablishment.isPresent() ) {

    		standardResponse.getErrors().add("There is no Establishment with the id: " +purchaseDto.getEstablishmentId() );
    		
    		return ResponseEntity.badRequest().body( standardResponse );
    	}    			
	
    	
		//---

   
		switch( optEestablishment.get().getEstablishmentType() ){

		/* Tipos de estabelcimentos
             1 - Alimentação (food)
             2 - Farmácia (DrugStore)
             3 - Posto de combustivel (Fuel)
        */
		
		case 1:
			
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback  = ( purchaseDto.getValue() / 100) * 10;
			Double valueWithDiscount = purchaseDto.getValue() - cashback;
			
			card.setBalance( card.getBalance() - valueWithDiscount );
			
			cardService.save( card );
			
			break;
			
		case 2:
			
			card.setBalance( card.getBalance() - purchaseDto.getValue()  );
			cardService.save( card );
			
			break;
			
		case 3:
			
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax  = ( purchaseDto.getValue() / 100) * 35;
			Double valueWithTax =  purchaseDto.getValue() + tax;
			
			card.setBalance( valueWithTax );
			cardService.save( card );
			
			break;
		}    	
    	

		//---   	
    	
    	Purchase purchase = convertPurchaseDtoToPurchase(purchaseDto, card, optEestablishment);

		//---

		Purchase purchaseSaved =  purchaseService.save( purchase );
 
    	standardResponse.setResponseContent( purchaseSaved );
    	
        return ResponseEntity.ok().body( standardResponse );
    }


	private Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto, Card card, Optional<Establishment> optEestablishment) {
		
		Purchase purchase = new Purchase();
		
    	purchase.setDateBuy( purchaseDto.getDateBuy() );
    	purchase.setCard( card );
    	purchase.setEstablishment( optEestablishment.get() );
    	purchase.setProductDescription( purchaseDto.getProductDescription() );
    	purchase.setValue( purchaseDto.getValue() );
    	
		return purchase;
	}


}