package br.com.alelo.consumer.consumerpat.util.converter;

import br.com.alelo.consumer.consumerpat.data.model.Card;
import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomConsumerConverter {

    private CustomConsumerConverter(){}

    public static ConsumerVO parseObject(Consumer entity){
        var vo = new ConsumerVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setDocumentNumber(entity.getDocumentNumber());
        vo.setBirthDate(entity.getBirthDate());

        //contacts
        vo.setMobilePhoneNumber(entity.getMobilePhoneNumber());
        vo.setResidencePhoneNumber(entity.getResidencePhoneNumber());
        vo.setPhoneNumber(entity.getPhoneNumber());
        vo.setEmail(entity.getEmail());

        //Address
        vo.setStreet(entity.getAddress().getStreet());
        vo.setNumber(entity.getAddress().getNumber());
        vo.setCity(entity.getAddress().getCity());
        vo.setCountry(entity.getAddress().getCountry());
        vo.setPostalCode(entity.getAddress().getPostalCode());

        //cards
        List<Card> cards = entity.getCards();
        cards.forEach(card -> {
            switch (card.getType()) {
                case FOOD: vo.setFoodCardBalance(card.getBalance());
                    vo.setFoodCardNumber(card.getNumber());
                    break;
                case FUEL: vo.setFuelCardBalance(card.getBalance());
                    vo.setFuelCardNumber(card.getNumber());
                    break;
                case DRUGSTORE: vo.setDrugstoreCardBalance(card.getBalance());
                    vo.setDrugstoreCardNumber(card.getNumber());
                    break;
            }
        });
        return vo;
    }

   public static List<ConsumerVO> parseList(List<Consumer> listEntity) {
        return listEntity.stream()
                .map( vote ->  {
                    var entity = parseObject(vote);
                    return entity;
                })
                .collect(Collectors.toList());
    }

}
