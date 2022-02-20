package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.adapter.CardAdapter;
import br.com.alelo.consumer.consumerpat.adapter.ExtractAdapter;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.card.ICard;
import br.com.alelo.consumer.consumerpat.enums.ECard;
import br.com.alelo.consumer.consumerpat.exception.ValidateException;
import br.com.alelo.consumer.consumerpat.respository.CardRepostiory;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.vo.BuyVo;
import br.com.alelo.consumer.consumerpat.vo.CardVo;
import br.com.alelo.consumer.consumerpat.vo.ExtractVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Log4j2
@Service
public class CardService {

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private CardRepostiory cardRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    public CardVo setBalance(CardVo cardVo) {

        Card card = cardRepository.findById(cardVo.getNumber()).orElseThrow(() ->  new ValidateException("Cartão não encontrado"));
        card.setBalance(card.getBalance().add(cardVo.getBalance()));
        cardRepository.saveAndFlush(card);
        cardVo = CardAdapter.modelToVo(card);

        return cardVo;
    }

    public ExtractVo buy(BuyVo buyVo) {

        ICard cardStr;

        try{
            cardStr = (ICard) ECard.findById(buyVo.getEstablishmentType()).getCard().getDeclaredConstructors()[0].newInstance();
        } catch (Exception ex){
            throw new ValidateException(ex.getMessage());
        }
        Card card = cardRepository.findById(buyVo.getCardNumber()).orElseThrow(() -> new ValidateException("Cartão não encontrado"));
        card.setBalance(card.getBalance().subtract(Optional.ofNullable(cardStr).orElseThrow().calculateBalance(buyVo.getValue())));
        cardRepository.save(card);

        Establishment establishment = Establishment.builder().Id(buyVo.getEstablishmentId()).build();
        Extract extract = Extract.builder().dateBuy(LocalDate.now()).establishment(establishment).productDescription(buyVo.getProductDescription()).card(card)
                                  .value(buyVo.getValue()).build();
        extractRepository.save(extract);
        ExtractVo extractVo = ExtractAdapter.modelToVo(extract);

        return extractVo;
    }

    public CardVo createCard(CardVo cardVo) {
        Card card = CardAdapter.voToModel(cardVo);
        card.setConsumer(consumerRepository.findById(cardVo.getConsumerId()).get());
        cardVo = CardAdapter.modelToVo(cardRepository.save(card));
        return cardVo;
    }
}
