package br.com.alelo.consumer.consumerpat.factory;

import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;

public interface PurchaseOperationFactory { 
    PurchaseOperationCommand createPurchaseOperation(PurchaseOperationCommand command);

}
