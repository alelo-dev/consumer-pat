package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.exceptions.IllegalArgumentException;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    /**
     * Método responsável por creditar um valor ao cartão do usuário
     */
    public Card setBalance(String cardNumber, Double value) {
        Card obj = repository.findByCardNumber(cardNumber);

        if(Objects.nonNull(obj)) {
            obj.setCardBalance(obj.getCardBalance() + value);
            return repository.save(obj);
        }
        throw new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Card.class.getSimpleName());
    }

    /**
     * Método responsável por realizar o débito do cartão do
     * usuário de acordo com o tipo de compra realizada
     */
    public void buy(Purchase purchase) {
        Card card = repository.findByCardNumber(purchase.getCardNumber());

        if(Objects.nonNull(card)) {
            if(purchase.getEstablishmentType().getDescription().equals("FOOD")
                    && card.getCardType().getDescription().equals("FOOD")) {
                // Para compras com cartão alimentação haverá um desconto de 10%
                card.setCardBalance(card.getCardBalance() - ((purchase.getValue() * .9)));
                repository.save(card);

            } else if(purchase.getEstablishmentType().getDescription().equals("DRUG_STORE")
                    && card.getCardType().getDescription().equals("DRUG_STORE")) {
                // Para compras com cartão de farmácia não haverá desconto ou acréscimo
                card.setCardBalance(card.getCardBalance() - purchase.getValue());
                repository.save(card);

            } else if(purchase.getEstablishmentType().getDescription().equals("FUEL")
                    && card.getCardType().getDescription().equals("FUEL")){
                // Para compras com cartão combustível haverá um acréscimo de 35%
                purchase.setValue(purchase.getValue() + purchase.getValue() * .35);
                card.setCardBalance(card.getCardBalance() - purchase.getValue());
                repository.save(card);

            } else {
                throw new IllegalArgumentException("Cartão não valido para este estabelecimento");
            }
            generateStatement(purchase);
        } else {
            throw new ObjectNotFoundException("Objeto Não encontrado. Tipo: " + Card.class.getSimpleName());
        }
    }

    /**
     * Método que irá gerar o extrato da compra realizada
     */
    private void generateStatement(Purchase purchase) {
        extractRepository.save(new Extract(
                null,
                purchase.getEstablishmentName(),
                purchase.getProductDescription(),
                LocalDateTime.now(),
                purchase.getCardNumber(),
                purchase.getValue()));
    }

}
