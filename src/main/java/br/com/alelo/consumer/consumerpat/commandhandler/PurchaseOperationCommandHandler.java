package br.com.alelo.consumer.consumerpat.commandhandler;

import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.factory.DrugStorePurchaseOperation;
import br.com.alelo.consumer.consumerpat.factory.FoodPurchaseOperation;
import br.com.alelo.consumer.consumerpat.factory.FuelStorePurchaseOperation;

@Component
public class PurchaseOperationCommandHandler implements CommandHandler<PurchaseOperationCommand, Void> {

  @Autowired
  ConsumerRepository repository;

  @Autowired
  ExtractRepository extractRepository;

	@Override
	public Void handle(PurchaseOperationCommand command) {


		if (command.getEstablishmentType() == 1) {
            FoodPurchaseOperation foodPurchaseOperation = new FoodPurchaseOperation(repository, extractRepository);
            foodPurchaseOperation.createPurchaseOperation(command);

        }else if(command.getEstablishmentType() == 2) {
            DrugStorePurchaseOperation drugStorePurchaseOperation = new DrugStorePurchaseOperation(repository, extractRepository);
            drugStorePurchaseOperation.createPurchaseOperation(command);

        } else {
            FuelStorePurchaseOperation fuelStorePurchaseOperation = new FuelStorePurchaseOperation(repository, extractRepository);
            fuelStorePurchaseOperation.createPurchaseOperation(command);
        }
		return null;
	}

}
