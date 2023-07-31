package br.com.alelo.consumer.consumerpat.infrastructure.repository.card;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter.CardBalanceConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter.CardConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardBalanceEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardRepositoryTest {

    @Mock
    private CardJpaRepository cardJpaRepository;

    @Mock
    private CardBalanceJpaRepository cardBalanceJpaRepository;

    @Mock
    private CardConverter cardConverter;

    @Mock
    private CardBalanceConverter cardBalanceConverter;

    @InjectMocks
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCard() {
        UUID cardId = UUID.randomUUID();
        Card card = createMockCard(cardId);
        CardEntity cardEntity = createMockCardEntity(cardId);
        CardBalance cardBalance = card.getCardBalance();
        CardBalanceEntity cardBalanceEntity = cardEntity.getCardBalance();

        when(cardConverter.toEntity(card)).thenReturn(cardEntity);

        cardRepository.saveCard(card);

        verify(cardConverter).toEntity(card);
        assertNotNull(cardEntity.getCreatedAt());
        assertNotNull(cardBalanceEntity.getCreatedAt());
        assertEquals(cardId, cardBalanceEntity.getId());
        assertEquals(cardId.toString(), cardBalanceEntity.getCardNumber());
    }

    @Test
    void testSaveCardBalance() {
        UUID cardId = UUID.randomUUID();
        Card card = createMockCard(cardId);
        CardEntity cardEntity = createMockCardEntity(cardId);
        CardBalance cardBalance = card.getCardBalance();
        CardBalanceEntity cardBalanceEntity = cardEntity.getCardBalance();

        when(cardBalanceConverter.toEntity(cardBalance)).thenReturn(cardBalanceEntity);
        when(cardJpaRepository.save(cardEntity)).thenReturn(cardEntity);

        cardRepository.saveCardBalance(cardBalance);

        verify(cardBalanceConverter).toEntity(cardBalance);
        assertNotNull(cardBalanceEntity.getUpdatedAt());
    }

    @Test
    void testFindCardBalanceByCardNumber() {
        String cardNumber = "1234567890123456";
        CardBalance cardBalance = new CardBalance(new CardNumber(cardNumber));
        CardBalanceEntity cardBalanceEntity = createMockCardBalanceEntity(UUID.randomUUID());
        cardBalanceEntity.setCardNumber(cardNumber);

        when(cardBalanceJpaRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(cardBalanceEntity));
        when(cardBalanceConverter.toDomain(cardBalanceEntity)).thenReturn(cardBalance);

        Optional<CardBalance> result = cardRepository.findCardBalanceByCardNumber(new CardNumber(cardNumber));

        assertTrue(result.isPresent());
        assertEquals(cardBalance, result.get());
        verify(cardBalanceJpaRepository).findByCardNumber(cardNumber);
        verify(cardBalanceConverter).toDomain(cardBalanceEntity);
    }

    @Test
    void testFindCardByCardNumber() {
        UUID cardId = UUID.randomUUID();
        Card card = createMockCard(cardId);
        CardEntity cardEntity = createMockCardEntity(cardId);

        when(cardJpaRepository.findByCardNumber(cardEntity.getCardNumber())).thenReturn(Optional.of(cardEntity));
        when(cardConverter.toDomain(cardEntity)).thenReturn(card);

        Optional<Card> result = cardRepository.findCardByCardNumber(new CardNumber(cardEntity.getCardNumber()));

        assertTrue(result.isPresent());
        assertEquals(card, result.get());
        verify(cardJpaRepository).findByCardNumber(cardEntity.getCardNumber());
        verify(cardConverter).toDomain(cardEntity);
    }

    @Test
    void testFindCardByConsumerId() {
        UUID consumerId = UUID.randomUUID();
        Set<Card> cards = new HashSet<>();
        cards.add(createMockCard(UUID.randomUUID()));
        cards.add(createMockCard(UUID.randomUUID()));
        Set<CardEntity> cardEntities = new HashSet<>();
        cardEntities.add(createMockCardEntity(UUID.randomUUID()));
        cardEntities.add(createMockCardEntity(UUID.randomUUID()));

        when(cardJpaRepository.findAllByConsumerId(consumerId)).thenReturn(Optional.of(cardEntities));
        when(cardConverter.toDomain(cardEntities)).thenReturn(cards);

        Optional<Set<Card>> result = cardRepository.findCardByConsumerId(consumerId);

        assertTrue(result.isPresent());
        assertEquals(cards, result.get());
        verify(cardJpaRepository).findAllByConsumerId(consumerId);
        verify(cardConverter).toDomain(cardEntities);
    }

    private CardEntity createMockCardEntity(UUID id) {
        CardBalanceEntity cardBalanceEntity = CardBalanceEntity.builder()
                .id(id)
                .cardNumber(id.toString())
                .amount(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        return CardEntity.builder()
                .cardNumber(id.toString())
                .cardType(CardType.FOOD)
                .consumer(null)
                .cardBalance(cardBalanceEntity)
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Card createMockCard(UUID id) {
        return new Card(new CardNumber(id.toString()), CardType.FOOD);
    }

    private CardBalanceEntity createMockCardBalanceEntity(UUID id) {
        return CardBalanceEntity.builder()
                .id(id)
                .cardNumber(id.toString())
                .amount(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
