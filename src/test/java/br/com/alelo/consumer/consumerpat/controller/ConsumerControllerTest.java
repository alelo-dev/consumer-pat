package br.com.alelo.consumer.consumerpat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.commandhandler.CreateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.ListAllConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.PurchaseOperationCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.UpdateCardBalanceCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.UpdateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.utils.Stubs;




@SpringBootTest
public class ConsumerControllerTest {

    @InjectMocks
    ConsumerController consumerController;

    @Mock
    ListAllConsumerCommandHandler listAllConsumerCommandHandler;

    @Mock
    CreateConsumerCommandHandler createConsumerCommandHandler;

    @Mock
    UpdateConsumerCommandHandler updateConsumerCommandHandler;

    @Mock
    UpdateCardBalanceCommandHandler updateCardBalanceCommandHandler;

    @Mock
    PurchaseOperationCommandHandler purchaseOperationCommandHandler;

    private static final String id = "93f60687-f260-40d1-835f-10165c113dc5";


    @Test
    public void testConsumerList() {
        var obj = Stubs.consumerListStub();
        var page = new PageImpl<>(obj);
        when(listAllConsumerCommandHandler.handle(null)).thenReturn(page);

        var mock = consumerController.listAllConsumers();

        assertEquals(obj, mock.getContent());
        assertNotNull(mock);
        assertEquals(PageImpl.class, mock.getClass());
    }

    @Test
    public void testCreateConsumer() {
        var obj = Stubs.consumerStub();
        when(createConsumerCommandHandler.handle(Stubs.consumerCommand())).thenReturn(obj);

        var mock = consumerController.createConsumer(Stubs.consumerCommand());

        assertEquals(obj, mock.getBody());
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getBody().getClass());
        assertEquals(HttpStatus.CREATED, mock.getStatusCode());
    }




    @Test
    public void testUpdateConsumer() {
        var obj = Stubs.consumerUpdatedStub();
        var response = ResponseEntity.ok(obj);
        when(updateConsumerCommandHandler.handle(Stubs.consumerUpdateCommand())).thenReturn(obj);

        var mock = consumerController.updateConsumer(id, Stubs.consumerUpdateCommand());

        assertEquals(response.getBody(), mock.getBody());
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getBody().getClass());
        assertEquals(HttpStatus.OK, mock.getStatusCode());
    }

    @Test
    public void testSetBalance(){
        var obj = Stubs.consumerStub();
        when(updateCardBalanceCommandHandler.handle(Stubs.updateCardBalanceCommand())).thenReturn(obj);

        var mock = consumerController.setBalance(Stubs.updateCardBalanceCommand());

        assertEquals(obj, mock.getBody());
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getBody().getClass());
        assertEquals(HttpStatus.OK, mock.getStatusCode());
    }

    @Test
    public void testBuy(){
        doNothing().when(purchaseOperationCommandHandler).handle(Stubs.purchaseOperationCommand());

        var mock = consumerController.buy(Stubs.purchaseOperationCommand());

        assertNull(mock.getBody());
        assertNotNull(mock);
        assertEquals(HttpStatus.OK, mock.getStatusCode());
    }
}


    

