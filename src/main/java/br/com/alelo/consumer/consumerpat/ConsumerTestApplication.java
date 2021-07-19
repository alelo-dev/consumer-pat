package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contacts;
import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ConsumerTestApplication implements CommandLineRunner {

    @Autowired
    ConsumerRepository consumerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buildConsumers(1000);
    }

    private void buildConsumers(int qntds) {
        List<Consumer> consumerList = new ArrayList<>();
        for (int i = 0; i < qntds; i++) {
            consumerList.add(buildConsumerData(i));
        }
        consumerRepository.saveAll(consumerList);
    }

    @SneakyThrows
    private Consumer buildConsumerData(int i) {
        Random random = new Random();
        List<Address> addressList = new ArrayList<>();
        List<Contacts> contactsList = new ArrayList<>();
        List<Card> cardsList = new ArrayList<>();

        if (random.nextBoolean()) {
            Address addressA = Address.builder()
                    .street("Rua do endereço " + i)
                    .number(125 + random.nextInt(1000))
                    .postalCode("654321564")
                    .country("Pais " + i)
                    .city("Cidade " + i)
                    .build();

            addressList.add(addressA);
        }

        if (random.nextBoolean()) {
            Card foodCard = Card.builder()
                    .cardNumber(generateCardNumber())
                    .cardBalance(BigDecimal.valueOf(random.nextInt(1000) * random.nextDouble()).setScale(2, RoundingMode.FLOOR))
                    .typeCard(CardType.FOOD_CARD)
                    .build();
            cardsList.add(foodCard);
        }

        if (random.nextBoolean()) {
            Card fuelCard = Card.builder()
                    .cardNumber(generateCardNumber())
                    .cardBalance(BigDecimal.valueOf(random.nextInt(1000) * random.nextDouble()).setScale(2, RoundingMode.FLOOR))
                    .typeCard(CardType.FUEL_CARD)
                    .build();
            cardsList.add(fuelCard);
        }

        if (random.nextBoolean()) {

            Card drugstoreCard = Card.builder()
                    .cardNumber(generateCardNumber())
                    .cardBalance(BigDecimal.valueOf(random.nextInt(1000) * random.nextDouble()).setScale(2, RoundingMode.FLOOR))
                    .typeCard(CardType.DRUG_STORE)
                    .build();
            cardsList.add(drugstoreCard);
        }

        if (random.nextBoolean()) {
            Contacts contacts = Contacts.builder()
                    .email("contact01@gmail.com")
                    .mobilePhoneNumber("(85)989881122")
                    .residencePhoneNumber("(85) 989881122")
                    .phoneNumber("(85) 989881122")
                    .build();
            contactsList.add(contacts);
        }

        return Consumer.builder()
                .name("consumer 01")
                .documentNumber("documentNumber")
                .birthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"))
                .contactsList(contactsList)
                .cardsList(cardsList)
                .addressList(addressList)
                .build();

    }

    private String generateCardNumber() {
        return String.valueOf(new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 16);
    }
}
