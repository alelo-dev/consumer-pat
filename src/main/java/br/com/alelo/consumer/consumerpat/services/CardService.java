package br.com.alelo.consumer.consumerpat.services;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CardService {

    private final @NonNull
    CardRepository repository;

    private final @NonNull
    ExtractService extractService;

    public void setBalance(Long cardNumber, Double value) throws BussinessException {

        if (value == null || value <= 0.0) {
            throw new BussinessException("Verifique o valor a ser adicionado ao cartão :" + value);
        }

        Optional<Card> optionalCard = repository.findByNumber(cardNumber);

        if (optionalCard.isEmpty()) {
            throw new BussinessException("Cartão inexistente");
        }

        Card card = optionalCard.get();
        card.setBalance(card.getBalance() + value);
        repository.save(card);
    }

    public Extract buy(int establishmentType, String establishmentName,
                       Long cardNumber, String productDescription, Double value) throws BussinessException {

        Optional<Card> optionalCard = repository.findByNumber(cardNumber);
        Extract extrato = null;

        Double cashback = 0.0;
        Double taxa = 0.0;

        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();

            if (isFood(card)) {
                cashback = value * 0.1;
            } else if (isFuel(card)) {
                taxa = value * 0.35;
            }

            if (card.getType().getCodigo() != (establishmentType)) {
                throw new BussinessException("Tipo do estabelecimento é diferente do tipo do cartão usado");
            }

            if (card.getBalance() < (value - cashback) || card.getBalance() < (value + taxa)) {
                throw new BussinessException("Saldo insulficiente");
            }

            card.setBalance(card.getBalance() - (value - cashback + taxa));
            repository.save(card);

            extrato = extractService.save(establishmentType, establishmentName,
                    productDescription, new Date(), cardNumber, value, cashback, taxa);
        }
        return extrato;
    }

    private boolean isFuel(Card card) {
        return card.getType().equals(CardType.FUEL);
    }

    private boolean isFood(Card card) {
        return card.getType().equals(CardType.FOOD);
    }
}
