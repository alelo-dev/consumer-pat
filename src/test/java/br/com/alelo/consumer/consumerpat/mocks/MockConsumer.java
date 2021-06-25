package br.com.alelo.consumer.consumerpat.mocks;

import br.com.alelo.consumer.consumerpat.data.model.Address;
import br.com.alelo.consumer.consumerpat.data.model.Card;
import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockConsumer {

    public ConsumerVO mockVO(){
        return mockVO(0);
    }

    public Consumer mockEntity(){
        return mockEntity(0);
    }

    private Consumer mockEntity(Integer number) {
        var entity = new Consumer();
        entity.setId(number);
        entity.setEmail("Email Test"+number);
        entity.setPhoneNumber(number);
        entity.setDocumentNumber(number.toString());
        entity.setBirthDate(new Date());
        entity.setMobilePhoneNumber(number);
        entity.setResidencePhoneNumber(number);
        entity.setName("Name Test"+number);

        Address address = new Address();
        address.setConsumerId(number);
        address.setStreet("Street Test"+number);
        address.setNumber(number);
        address.setCountry("Country Test"+number);
        address.setCity("City Test"+number);
        address.setId(number);
        address.setPostalCode(number.toString());
        entity.setAddress(address);

        List<Card> cards = new ArrayList<>();
        Card food = new Card(1, 1111111111111111L, new BigDecimal(100), TypeCardsEnum.FOOD, entity);
        Card drugstore = new Card(2, 2222222222222222L, new BigDecimal(100), TypeCardsEnum.DRUGSTORE, entity);
        Card fuel = new Card(3, 3333333333333333L, new BigDecimal(100), TypeCardsEnum.FUEL, entity);
        cards.add(fuel);cards.add(food); cards.add(drugstore);
        entity.setCards(cards);

        return entity;
    }

    private ConsumerVO mockVO(Integer number) {
        var vo = new ConsumerVO();
        vo.setId(number);
        vo.setName("Name Test" + number);
        vo.setDocumentNumber(number.toString());
        vo.setBirthDate(new Date());

        //contacts
        vo.setMobilePhoneNumber(number);
        vo.setResidencePhoneNumber(number);
        vo.setPhoneNumber(number);
        vo.setEmail("Email Test"+number);

        //Address
        vo.setStreet("Street Test"+number);
        vo.setNumber(number);
        vo.setCity("City Test"+number);
        vo.setCountry("Country Test"+number);
        vo.setPostalCode(number.toString());

        vo.setFoodCardNumber(1111111111111111L);
        vo.setFoodCardBalance(new BigDecimal(number));
        vo.setDrugstoreCardNumber(2222222222222222L);
        vo.setDrugstoreCardBalance(new BigDecimal(number));
        vo.setFuelCardNumber(333333333333333L);
        vo.setFuelCardBalance(new BigDecimal(number));
        return vo;
    }

    public List<ConsumerVO> mockVOList() {
        List<ConsumerVO> vos = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            vos.add(mockVO(i));
        }
        return vos;
    }

    public List<Consumer> mockEntityList() {
        List<Consumer> entities = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            entities.add(mockEntity(i));
        }
        return entities;
    }
}
