package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CardCreditServiceTest {

    @InjectMocks
    private CardCreditService cardCreditService;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetBalance_Success() {
        // Simula a busca do cartão por número
        when(cardRepository.findCardByCardNumber(anyInt())).thenReturn(Optional.of(new Card(1, 545464, 150.0, 2,5)));

        // Chama o método setBalance
        assertDoesNotThrow(() -> cardCreditService.setBalance(123456789, 50.0));

        // Verifica se o método save foi chamado
        verify(cardRepository).save(any(Card.class));
    }

    @Test
    public void testSetBalance_CardNotFound() {
        // Simula a busca do cartão por número que não existe
        when(cardRepository.findCardByCardNumber(anyInt())).thenReturn(Optional.empty());

        // Chama o método setBalance e verifica se uma exceção é lançada
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> cardCreditService.setBalance(987654321, 30.0));

        // Verifica se o método save não foi chamado
        verify(cardRepository, never()).save(any(Card.class));
    }

    @Test
    public void testSave_Success() {
        // Simula a busca do cartão por número
        when(cardRepository.findCardByCardNumber(anyInt())).thenReturn(Optional.empty());

        // Simula o salvamento do cartão
        when(cardRepository.save(any(Card.class))).thenReturn(new Card(123456789, 100.0));

        Card card = new Card();
        card.setCardNumber(123456789);
        card.setId(1);

        // Chama o método save
        Card savedCard = cardCreditService.save(card);

        // Verifica se o cartão foi salvo corretamente
        assertNotNull(savedCard);
        assertEquals(123456789, savedCard.getCardNumber());
        assertEquals(100.0, savedCard.getCardBalance());
    }

    @Test
    public void testSave_CardExists() {
        // Simula a busca do cartão por número que já existe
        when(cardRepository.findCardByCardNumber(anyInt()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    // Obter o argumento passado para o método (número do cartão)
                    Integer cardNumber = invocation.getArgument(0);

                    // Criar um objeto Card com base no número do cartão
                    Card card = new Card();
                    card.setCardNumber(cardNumber);
                    card.setCardBalance(100.0);

                    // Retornar um Optional com o objeto Card criado
                    return Optional.of(card);
                });

        Card card = new Card();
        card.setId(1);
        card.setCardNumber(123456789);
        card.setCardBalance(50.0);


        // Chama o método save e verifica se retorna null
        Card savedCard = cardCreditService.save(card);

        assertNull(savedCard);

        // Verifica se o método save não foi chamado
        verify(cardRepository, never()).save(any(Card.class));
    }
}

