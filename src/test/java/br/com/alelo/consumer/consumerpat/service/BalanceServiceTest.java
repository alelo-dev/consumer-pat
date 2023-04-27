package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.excepion.CardNotFoundExeption;
import br.com.alelo.consumer.consumerpat.excepion.NoBalanceException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BalanceServiceTest {

    private ConsumerRepository consumerRepository;
    private ExtractRepository extractRepository;
    private BalanceService service;

    BalanceServiceTest() {
        consumerRepository = mock(ConsumerRepository.class);
        extractRepository = mock(ExtractRepository.class);
        service = new BalanceService(consumerRepository, extractRepository);
    }
    @Test
    public void shouldThrowExeptionWhenTryTosetCreditWithInvalidEstablishmentType() {
        assertThrows(CardNotFoundExeption.class, () -> {
            service.setCredit(5L, 10.0);
        });
    }

    @Test
    public void shouldReturnSucessWhenSetCreditWithValidInfoOfFood() {
        Consumer consumer =  Consumer.builder().foodCardBalance(10.0).build();
        when(consumerRepository.findFirstByFoodCardNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.setCredit(1L, 100.0);

        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldReturnSucessWhenSetCreditWithValidInfoOfDrug() {
        Consumer consumer =  Consumer.builder().drugstoreCardBalance(10.0).build();
        when(consumerRepository.findFirstByDrugstoreNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.setCredit(2L, 100.0);

        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldReturnSucessWhenSetCreditWithValidInfoOfFuel() {
        Consumer consumer =  Consumer.builder().fuelCardBalance(10.0).build();
        when(consumerRepository.findFirstByFuelCardNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.setCredit(2L, 100.0);

        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldThrowExeptionWhenTryToBuyWithInvalidEstablishmentType() {
        Consumer consumer =  Consumer.builder().foodCardBalance(10.0).build();
        when(consumerRepository.findFirstByFoodCardNumber(anyLong())).thenReturn(consumer);
        assertThrows(CardNotFoundExeption.class, () -> {
            service.buy(5,"Estabelecimento", 1111111111111111l, "Almoco",  10.0);
        });
    }

    @Test
    public void shouldThrowExeptionWhenTryToBuyWithInvalidAmount() {
        Consumer consumer =  Consumer.builder().foodCardBalance(10.0).build();
        when(consumerRepository.findFirstByFoodCardNumber(anyLong())).thenReturn(consumer);
        assertThrows(NoBalanceException.class, () -> {
            service.buy(1,"Estabelecimento", 1111111111111111l, "Almoco",  100.0);
        });
    }

    @Test
    public void shouldReturnSucessWhenBuyFoodWithValidInfo() {
        Consumer consumer =  Consumer.builder().foodCardBalance(10.0).build();
        when(consumerRepository.findFirstByFoodCardNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.buy(1,"Estabelecimento", 1111111111111111l, "Almoco",  10.0);

        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldReturnSucessWhenBuyDrugsWithValidInfo() {
        Consumer consumer =  Consumer.builder().drugstoreCardBalance(10.0).build();
        when(consumerRepository.findFirstByDrugstoreNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.buy(2,"Estabelecimento", 1111111111111111l, "Almoco",  10.0);

        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldReturnSucessWhenBuyFuelWithValidInfo() {
        Consumer consumer =  Consumer.builder().fuelCardBalance(10.0).build();
        when(consumerRepository.findFirstByFuelCardNumber(anyLong())).thenReturn(consumer);
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);
        service.buy(3,"Estabelecimento", 1111111111111111l, "Almoco",  7.0);


        verify(consumerRepository).save(any(Consumer.class));
    }

}
