package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CheckoutDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.checkout.CheckoutContext;
import br.com.alelo.consumer.consumerpat.service.checkout.DrugstoreCheckout;
import br.com.alelo.consumer.consumerpat.service.checkout.FoodCheckout;
import br.com.alelo.consumer.consumerpat.service.checkout.FuelCheckout;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CheckoutService {

    private final CardRepository cardRepository;

    private final EstablishmentRepository establishmentRepository;

    private final ExtractRepository extractRepository;

    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    public void checkout(CheckoutDTO checkoutDto) {
        String establishmentName = checkoutDto.getEstablishmentName();
        EstablishmentType establishmentType = checkoutDto.getEstablishmentType();
        BigDecimal value = checkoutDto.getValue();

        Establishment establishment = establishmentRepository.findByNameAndType(establishmentName, establishmentType)
                 .orElseGet(() -> {
                    return establishmentRepository.save(new Establishment(null, establishmentName, establishmentType));
        });

        Card card = cardRepository.findByCardNumber(checkoutDto.getCardNumber())
                .orElseThrow(ResourceNotFoundException::new);

        CheckoutContext checkoutContext = new CheckoutContext(new FoodCheckout());
        if (EstablishmentType.DRUGSTORE.equals(establishmentType)) {
            checkoutContext.setCheckoutStrategy(new DrugstoreCheckout());
        } else if (EstablishmentType.FUEL.equals(establishmentType)) {
            checkoutContext.setCheckoutStrategy(new FuelCheckout());
        }

        BigDecimal subtractValue = checkoutContext.validateAndCalculate(card, value);
        card.setBalance(card.getBalance().subtract(subtractValue));
        cardRepository.save(card);

        Extract extract = new Extract(null, LocalDateTime.now(), establishment, card, value, checkoutDto.getProductDescription());
        extractRepository.save(extract);
    }

}
