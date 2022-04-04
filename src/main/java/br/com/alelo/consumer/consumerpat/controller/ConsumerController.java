package br.com.alelo.consumer.consumerpat.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.response.StandardResponse;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.util.ObjectConverter;
import br.com.alelo.consumer.consumerpat.util.ReceivedParameterValidator;


@RestController 
@RequestMapping(value="/consumer", produces="application/json")
@CrossOrigin(origins = "*")
public class ConsumerController {

	
	private static final Logger log = LoggerFactory.getLogger( ConsumerController.class );
	
	
    @Autowired
    ConsumerService consumerService;

    
    /* Deve listar todos os clientes (cerca de 500) */    
    @GetMapping(value = "/consumerList")
    public ResponseEntity< StandardResponse< List<Consumer> > > listAllConsumers(){
    	
    	
    	StandardResponse< List<Consumer> > standardResponse = new StandardResponse< List<Consumer> >();
  
   		
    	List<Consumer> listOfConsumers = consumerService.getAllConsumersList();
        
    	if( listOfConsumers.isEmpty() ) {
    		
    		return ResponseEntity.noContent().build();
    	}
    	
        standardResponse.setResponseContent( listOfConsumers );
        
        return ResponseEntity.ok().body( standardResponse );    		
   
    }

  
    @GetMapping(value = "/getById/{id}")
    public ResponseEntity< StandardResponse<Consumer> > getConsumerById( @PathVariable("id") long id  ){
    	
    	log.info("Getting consumer with id: ", id );
    	
    	StandardResponse<Consumer> standardResponse = new StandardResponse<Consumer>();
  
   		
    	Optional<Consumer> optConsumer = consumerService.getConsumerById( id );
        

    	if( !optConsumer.isPresent() ) {
    		
    		return ResponseEntity.noContent().build();
    	}
    	
    	
        standardResponse.setResponseContent( optConsumer.get() );
        
        return ResponseEntity.ok().body( standardResponse );    		
   
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    public ResponseEntity< StandardResponse<Consumer> > createConsumer( @Valid @RequestBody ConsumerDto consumerDto, 
    		                                                                                BindingResult bindingResult,
    		                                                                                HttpServletResponse httpServletResponse) {

 
    	log.info("Creating consumer: ", consumerDto.getName() );    	
    	
    	
    	StandardResponse<Consumer> standardResponse = new StandardResponse<Consumer>();
    	
    	
		//------
		
		boolean validationResultContainsErrors = ReceivedParameterValidator.checkValidationResultBindingResult( bindingResult, standardResponse );
		if( validationResultContainsErrors ){
		
		return ResponseEntity.badRequest().body( standardResponse );
		
		}

		//------   	
    	
		
		Consumer consumer = ObjectConverter.convertConsumerDtoToConsumer( consumerDto );
		
		Consumer consumerSaved = consumerService.save( consumer );
    	
		
		//------     	

		URI uriToGetTheSavedConsumer = ServletUriComponentsBuilder.fromCurrentContextPath()
				                                                  .path("/consumer/getById/{id}")
			                                                      .buildAndExpand( consumerSaved.getId() )
			                                                      .toUri();
		
		//Aqui eu seto uri no "header" da resposta da requisicao.
		httpServletResponse.setHeader( "Location", uriToGetTheSavedConsumer.toASCIIString() );
		
		//------ 
			
    	standardResponse.setResponseContent( consumerSaved );
    	
		return ResponseEntity.created( uriToGetTheSavedConsumer ).body( standardResponse );
    }

  
    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/updateConsumer/{id}")
    public ResponseEntity< StandardResponse<Consumer> > updateConsumer( @PathVariable("id") long id,
    		                                                            @Valid @RequestBody ConsumerDto consumerDto, 
                                                                                            BindingResult bindingResult ) {

    	log.info("Updating consumer with id: ", id );     	
    	
    	StandardResponse<Consumer> standardResponse = new StandardResponse<Consumer>();    	
    	
    	
		//------
		
		boolean validationResultContainsErrors = ReceivedParameterValidator.checkValidationResultBindingResult( bindingResult, standardResponse );
		if( validationResultContainsErrors ){
		
		return ResponseEntity.badRequest().body( standardResponse );
		
		}
		
		//------     	
 

    	Optional<Consumer> optConsumer = consumerService.getConsumerById( id );
        
    	if( !optConsumer.isPresent() ) {

    		standardResponse.getErrors().add("There is no consumer with the id: " +id);
    		
    		return ResponseEntity.badRequest().body( standardResponse );
    	}		

    	
		//------ 
    	
    	Consumer consumerCurrent = optConsumer.get();
    	
    	updateConsumer(consumerDto, consumerCurrent);		
    	
    	Consumer consumerUpdated = consumerService.save( consumerCurrent );

		//------
    	
    	
    	standardResponse.setResponseContent( consumerUpdated );
    	
        return ResponseEntity.ok().body( standardResponse );  


    }


	private void updateConsumer(ConsumerDto consumerDto, Consumer consumerCurrent) {
		
		consumerCurrent.setName( consumerDto.getName() );
    	consumerCurrent.setDocumentNumber( consumerDto.getDocumentNumber() );
    	consumerCurrent.setBirthDate( consumerDto.getBirthDate() );
    	
    	consumerCurrent.getContact().setMobilePhoneNumber( consumerDto.getContactDto().getMobilePhoneNumber() );
    	consumerCurrent.getContact().setResidencePhoneNumber( consumerDto.getContactDto().getResidencePhoneNumber() );
    	consumerCurrent.getContact().setPhoneNumber( consumerDto.getContactDto().getPhoneNumber() );
    	consumerCurrent.getContact().setEmail( consumerDto.getContactDto().getEmail() );

    	consumerCurrent.getAddress().setStreet( consumerDto.getAddressDto().getStreet() );
    	consumerCurrent.getAddress().setNumber(consumerDto.getAddressDto().getNumber() );	
    	consumerCurrent.getAddress().setCity( consumerDto.getAddressDto().getCity() );
    	consumerCurrent.getAddress().setCountry( consumerDto.getAddressDto().getCountry() );
    	consumerCurrent.getAddress().setPostalCode( consumerDto.getAddressDto().getPostalCode() );
	}

}