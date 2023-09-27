package br.com.alelo.consumer.consumerpat.commandhandler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateCardBalanceCommand;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Component
public class UpdateCardBalanceCommandHandler implements CommandHandler<UpdateCardBalanceCommand, Consumer >{

    @Autowired
    ConsumerRepository repository;

    @Override
    public Consumer handle(UpdateCardBalanceCommand command) {

        var consumerCards = repository.findByFoodCardNumber1(command.getCardNumber());

        if(consumerCards == null){
            return null;
        }

        if(consumerCards.getFoodCardNumber() == command.getCardNumber()){
            consumerCards.setFoodCardBalance(consumerCards.getFoodCardBalance() + command.getValue());
        }else if(consumerCards.getFuelCardNumber() == command.getCardNumber()){
            consumerCards.setFuelCardBalance(consumerCards.getFuelCardBalance() + command.getValue());  
        }else if(consumerCards.getDrugstoreNumber() == command.getCardNumber()){
            consumerCards.setDrugstoreCardBalance(consumerCards.getDrugstoreCardBalance() + command.getValue());
        }   
        return  repository.save(consumerCards);
        
    }
    
}
