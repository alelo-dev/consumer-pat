package br.com.alelo.consumer.consumerpat.factory;

import java.util.Date;

import org.springframework.http.HttpStatus;

import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.DefaultException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.stereotype.Component;

@Component
public class FoodPurchaseOperation implements PurchaseOperationFactory{


    final ConsumerRepository repository;

    final ExtractRepository extractRepository;

  public FoodPurchaseOperation(ConsumerRepository repository, ExtractRepository extractRepository) {
    this.repository = repository;
    this.extractRepository = extractRepository;
  }


  @Override
	public PurchaseOperationCommand createPurchaseOperation(PurchaseOperationCommand command) {
        Double cashback  = (command.getValue() / 100) * 10;
        command.setValue(command.getValue() - cashback);

        var consumer = repository.findByFoodCardNumber(command.getCardNumber());

        if (consumer == null) throw new DefaultException(HttpStatus.BAD_REQUEST, "O número do cartão = " + command.getCardNumber() + " não existe e/ou não é do tipo Alimentação");

        if(consumer.getFoodCardBalance() >= command.getValue()){
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - command.getValue());
            repository.save(consumer);

            Extract extract = new Extract(command.getEstablishmentName(), command.getProductDescription(), new Date(),
                command.getCardNumber(), command.getValue());
            extractRepository.save(extract);

        }else if (consumer.getDrugstoreCardBalance() < command.getValue() ){
            throw new DefaultException(HttpStatus.BAD_REQUEST, "Saldo do cartão = " + command.getCardNumber() + " insuficiente");
        }
		return command;
	}

}
