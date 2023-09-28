package br.com.alelo.consumer.consumerpat.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateCardBalanceCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class Stubs {

    CreateConsumerCommand consumerCommand = new CreateConsumerCommand();

    public static CreateConsumerCommand consumerCommand() {
        CreateConsumerCommand consumerCommand = new CreateConsumerCommand();
        consumerCommand.setName("Ana");
        consumerCommand.setEmail("teste@teste.com");
        consumerCommand.setDocumentNumber(111111);
        consumerCommand.setDrugstoreNumber(22222);
        consumerCommand.setDrugstoreCardBalance(50.00);
        consumerCommand.setFoodCardNumber(33333);
        consumerCommand.setFoodCardBalance(100.00);
        consumerCommand.setFuelCardNumber(55555);
        consumerCommand.setFuelCardBalance(44.00);

        return consumerCommand;
    }

    public static Consumer consumerStub() {
        Consumer consumerCreated = new Consumer(consumerCommand());
        return consumerCreated;
    }

    public static List<Consumer> consumerListStub(){
        List<Consumer> consumerList = new ArrayList<>();
        Consumer consumerCreated = new Consumer(consumerCommand());
        consumerList.add(consumerCreated);
        return consumerList;
        
    }

    public static UpdateConsumerCommand consumerUpdateCommand() {
        UpdateConsumerCommand consumerUpdateCommand = new UpdateConsumerCommand();
        consumerUpdateCommand.setId("93f60687-f260-40d1-835f-10165c113dc5");
        consumerUpdateCommand.setName("Ana Update");
        consumerUpdateCommand.setEmail("teste-update@teste.com");
        consumerUpdateCommand.setDrugstoreNumber(77777);
        consumerUpdateCommand.setFoodCardNumber(33333);
        consumerUpdateCommand.setFuelCardNumber(55555);

        return consumerUpdateCommand;
    }

    public static Consumer consumerUpdatedStub(){
        Consumer consumerUpdated = consumerStub();
        consumerUpdated.setId("93f60687-f260-40d1-835f-10165c113dc5");
        consumerUpdated.setName("Ana Update");
        consumerUpdated.setEmail("teste-update@teste.com");
        consumerUpdated.setDrugstoreNumber(77777);
        consumerUpdated.setFoodCardNumber(33333);
        consumerUpdated.setFuelCardNumber(55555);

        return consumerUpdated;
    }

    public static UpdateCardBalanceCommand updateCardBalanceCommand() {
        UpdateCardBalanceCommand updateCardBalanceCommand = new UpdateCardBalanceCommand();
        updateCardBalanceCommand.setCardNumber(33333);
        updateCardBalanceCommand.setValue(100.00);

        return updateCardBalanceCommand;
    }

    public static PurchaseOperationCommand purchaseOperationCommand(){
        PurchaseOperationCommand purchaseOperationCommand = new PurchaseOperationCommand(1, 
            "Padaria Feliz", 33333, "Pães quentinhos", 15.00);

        return purchaseOperationCommand;
    }

    
}
