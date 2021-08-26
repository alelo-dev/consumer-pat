package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumerDTOTest {

    @Test
    public void parseConsumerEntity(){
        ConsumerDTO consumerDto = new ConsumerDTO();
        consumerDto.name = "windson";
        consumerDto.documentNumber = 6661;
        consumerDto.birthDate = new Date();

        consumerDto.mobilePhoneNumber = 666;
        consumerDto.residencePhoneNumber = 666;
        consumerDto.phoneNumber = 666;
        consumerDto.email = "windson@windson";

        consumerDto.street = "rua";
        consumerDto.number = 666;
        consumerDto.city = "rua";
        consumerDto.country = "cidade";
        consumerDto.portalCode = 666;

        consumerDto.foodCardNumber = 666;
        consumerDto.fuelCardNumber = 666;
        consumerDto.drugstoreNumber = 666;

        Consumer consumer = consumerDto.parseConsumer();
        Assert.isTrue(consumer.getContacts().size()==4,"Deve existir 4 contatos");
        Assert.isTrue(consumer.getAddresses().size()==1,"Deve existir 1 endereço");
        Assert.isTrue(consumer.getCards().size()==3,"Deve existir 3 cartoes");
        BigDecimal saldoTotal = consumer.getCards().stream().map(Card::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.isTrue(saldoTotal.compareTo(new BigDecimal(0)) == 0,"Todos os cartoes devem ter saldo zero");
    }
}
