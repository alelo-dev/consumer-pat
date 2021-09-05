package br.com.alelo.consumer.pat.service;

import br.com.alelo.consumer.pat.ConsumerFactory;
import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.entity.Consumer;
import br.com.alelo.consumer.pat.respository.CardRepository;
import br.com.alelo.consumer.pat.respository.ConsumerRepository;
import br.com.alelo.consumer.pat.respository.ExtractRepository;
import br.com.alelo.consumer.pat.strategy.ChangeValueStrategy;
import br.com.alelo.consumer.pat.strategy.DrugstoreValueChanger;
import br.com.alelo.consumer.pat.strategy.FoodChangeValue;
import br.com.alelo.consumer.pat.strategy.FuelChangeValue;
import br.com.alelo.consumer.pat.strategy.ValueChanger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceTest {

    public static final long INITIAL_BALANCE = 1000l;
    public static final int PRODUCT_PRICE = 100;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ExtractRepository extractRepository;

    private Map<String, ValueChanger> strategies;
    private ChangeValueStrategy changeValueStrategy;
    private TransactionService transactionService;
    private Consumer consumer;

    @Before
    public void setup() {
        strategies = Map.of("FOOD", new FoodChangeValue(), "FUEL", new FuelChangeValue(), "DRUGSTORE", new DrugstoreValueChanger());
        changeValueStrategy = new ChangeValueStrategy(strategies);
        transactionService = new TransactionService(cardRepository, extractRepository, changeValueStrategy);

        consumer = ConsumerFactory.createWithAllCardsWithBalance(BigDecimal.valueOf(INITIAL_BALANCE));

        consumerRepository.save(consumer);
    }

    @Test
    public void should_buy_drugstore_with_normal_pricing() {
        transactionService.buyProduct(EstablishmentType.DRUGSTORE, 1l, "Drugstore", "1000", "Medicine", BigDecimal.valueOf(PRODUCT_PRICE));

        Consumer consumer = consumerRepository.findAll().get(0);

        List<Card> cards = consumer.getCards().stream().filter(c -> c.getEstablishmentType() == EstablishmentType.DRUGSTORE).collect(Collectors.toList());

        assertThat(cards.get(0).getBalance().compareTo(BigDecimal.valueOf(INITIAL_BALANCE - PRODUCT_PRICE)));
    }

    @Test
    public void should_buy_food_with_10_percent_cashback() {
        transactionService.buyProduct(EstablishmentType.FOOD, 2l, "Dominos", "2000", "Pizza", BigDecimal.valueOf(PRODUCT_PRICE));

        Consumer consumer = consumerRepository.findAll().get(0);

        List<Card> cards = consumer.getCards().stream().filter(c -> c.getEstablishmentType() == EstablishmentType.FOOD).collect(Collectors.toList());

        assertThat(cards.get(0).getBalance().compareTo(BigDecimal.valueOf(INITIAL_BALANCE - PRODUCT_PRICE + 10)));
    }

    @Test
    public void should_buy_fuel_with_35_percent_tax() {
        transactionService.buyProduct(EstablishmentType.FUEL, 3l, "Shell", "3000", "Gas", BigDecimal.valueOf(PRODUCT_PRICE));

        Consumer consumer = consumerRepository.findAll().get(0);

        List<Card> cards = consumer.getCards().stream().filter(c -> c.getEstablishmentType() == EstablishmentType.FUEL).collect(Collectors.toList());

        assertThat(cards.get(0).getBalance().compareTo(BigDecimal.valueOf(INITIAL_BALANCE - PRODUCT_PRICE - 35)));
    }

}
