package br.com.alelo.consumer.consumerpat.commandhandler;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.exception.DefaultException;
import br.com.alelo.consumer.consumerpat.factory.DrugStorePurchaseOperation;
import br.com.alelo.consumer.consumerpat.factory.FoodPurchaseOperation;
import br.com.alelo.consumer.consumerpat.factory.FuelStorePurchaseOperation;
import br.com.alelo.consumer.consumerpat.factory.PurchaseOperationFactory;

@Component
public class PurchaseOperationCommandHandler implements CommandHandler<PurchaseOperationCommand, Void> {

  @Autowired
  ConsumerRepository repository;

  @Autowired
  ExtractRepository extractRepository;

	@Override
	public Void handle(PurchaseOperationCommand command) {  

        PurchaseOperationFactory factory;

		if (command.getEstablishmentType() == 1) {
            factory = new FoodPurchaseOperation(repository, extractRepository);
            factory.createPurchaseOperation(command);

        }else if(command.getEstablishmentType() == 2) {
            factory = new DrugStorePurchaseOperation(repository, extractRepository);
            factory.createPurchaseOperation(command);

        } else if(command.getEstablishmentType() == 3){
            factory = new FuelStorePurchaseOperation(repository, extractRepository);
            factory.createPurchaseOperation(command);
        } else {
          throw new DefaultException(HttpStatus.BAD_REQUEST, "Não existe este tipo de estabelecimento");
        }
		return null;
	}

}
