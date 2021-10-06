package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.services.exceptions.IllegalArgumentException;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Log4j2
@Service
public class CardServiceImpl implements CardService {

    private static final String CARD_SERVICE_METODO = "CARD_SERVICE ::: Entrou no método ";

    @Autowired
    private CardRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    public Card setBalance(String cardNumber, Double value) {
        log.info(CARD_SERVICE_METODO + "setBalance");
        Card obj = repository.findByCardNumber(cardNumber);

        if(Objects.nonNull(obj)) {
            obj.setCardBalance(obj.getCardBalance() + value);
            return repository.save(obj);
        }
        throw new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Card.class.getSimpleName());
    }

    public Card buy(Purchase purchase) {
        log.info(CARD_SERVICE_METODO + "buy");
        Card card = repository.findByCardNumber(purchase.getCardNumber());

        if(Objects.nonNull(card)) {
            if(purchase.getEstablishmentType().getDescription().equals("FOOD")
                    && card.getCardType().getDescription().equals("FOOD")) {
                // Para compras com cartão alimentação haverá um desconto de 10%
                card.setCardBalance(card.getCardBalance() - ((purchase.getValue() * .9)));
                card = repository.save(card);

            } else if(purchase.getEstablishmentType().getDescription().equals("DRUG_STORE")
                    && card.getCardType().getDescription().equals("DRUG_STORE")) {
                // Para compras com cartão de farmácia não haverá desconto ou acréscimo
                card.setCardBalance(card.getCardBalance() - purchase.getValue());
                card = repository.save(card);

            } else if(purchase.getEstablishmentType().getDescription().equals("FUEL")
                    && card.getCardType().getDescription().equals("FUEL")){
                // Para compras com cartão combustível haverá um acréscimo de 35%
                purchase.setValue(purchase.getValue() + purchase.getValue() * .35);
                card.setCardBalance(card.getCardBalance() - purchase.getValue());
                card = repository.save(card);

            } else {
                throw new IllegalArgumentException("Cartão não valido para este estabelecimento");
            }
            generateStatement(purchase);
        } else {
            throw new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Card.class.getSimpleName());
        }
        return card;
    }

    /**
     * Método que irá gerar o extrato da compra realizada
     */
    private void generateStatement(Purchase purchase) {
        log.info(CARD_SERVICE_METODO + "generateStatement");
        extractRepository.save(new Extract(
                null,
                purchase.getEstablishmentName(),
                purchase.getProductDescription(),
                LocalDateTime.now(),
                purchase.getCardNumber(),
                purchase.getValue()));
    }

    /**
     * Método verifica se existe um cartão com o numero
     * passado como parâmetro na criação de um novo usuario
     * If true uma exceção será lançada informando o usuario
     */
    public void validIfCardNumberAlreadyExists(Consumer consumer) {
        log.info(CARD_SERVICE_METODO + "validIfCardNumberAlreadyExists");
        for(Card x : consumer.getCards()) {
            Card card = repository.findByCardNumber(x.getCardNumber());
            if(card != null) {
                throw new DataIntegrityViolationException
                        ("Cartão já cadastrado no sistema. Número: " + x.getCardNumber());
            }
        }
    }

}
