package br.com.alelo.consumer.consumerpat.factory;

import java.util.Date;

import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.stereotype.Component;

@Component
public class FuelStorePurchaseOperation implements PurchaseOperationFactory{

  final ConsumerRepository repository;

  final ExtractRepository extractRepository;

  public FuelStorePurchaseOperation(ConsumerRepository repository, ExtractRepository extractRepository) {
    this.repository = repository;
    this.extractRepository = extractRepository;
  }

  @Override
	public PurchaseOperationCommand createPurchaseOperation(PurchaseOperationCommand command) {

        Double tax  = (command.getValue() / 100) * 35;
        command.setValue(command.getValue() + tax);

        var consumer = repository.findByFuelCardNumber(command.getCardNumber());

        if(consumer.getFuelCardBalance() >= command.getValue()){
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - command.getValue());
            repository.save(consumer);

            Extract extract = new Extract(command.getEstablishmentName(), command.getProductDescription(), new Date(),
                command.getCardNumber(), command.getValue());
            extractRepository.save(extract);

        }else{
            System.out.println("Saldo insuficiente");
        }
		return command;
	}

}
