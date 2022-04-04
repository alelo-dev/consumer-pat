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
import br.com.alelo.consumer.consumerpat.dto.SetCardBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.response.StandardResponse;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.util.ReceivedParameterValidator;


@RestController 
@RequestMapping(value="/card", produces="application/json")
@CrossOrigin(origins = "*")
public class CardController {


	@Autowired
	CardService cardService;
	

    @PostMapping(value = "/setcardbalance")
    public ResponseEntity< StandardResponse<Card> > setBalance( @Valid @RequestBody SetCardBalanceDto setBalanceDto, 
    		                                                                        BindingResult bindingResult ) {

    	StandardResponse<Card> standardResponse = new StandardResponse<Card>();     	

		//------
		
		boolean validationResultContainsErrors = ReceivedParameterValidator.checkValidationResultBindingResult( bindingResult, standardResponse );
		if( validationResultContainsErrors ){
		
		return ResponseEntity.badRequest().body( standardResponse );
		
		}

		
		//------
		
		
		Optional<Card> optCard = cardService.getCardById( setBalanceDto.getId() );
		
    	if( !optCard.isPresent() ) {

    		standardResponse.getErrors().add("There is no card with the id: " +setBalanceDto.getId() );
    		
    		return ResponseEntity.badRequest().body( standardResponse );
    	}
    	  	
    	
    	Card card = optCard.get();
   
    	card.setBalance( card.getBalance() + setBalanceDto.getValue() );
		
    	
    	cardService.save( card );
    	
    	
    	standardResponse.setResponseContent( card );
    	
        return ResponseEntity.ok().body( standardResponse ); 
    }

}