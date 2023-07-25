package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Payment {

    private UUID id;
    @NotNull(message = "Establishment is required")
    private Establishment establishment;
    @NotBlank(message = "Product description is required")
    private String productDescription;
    @NotNull(message = "Buy date is required")
    private LocalDate buyDate;
    @NotNull(message = "Card number is required")
    private CardNumber cardNumber;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    private PaymentStrategy paymentStrategy;

    public Payment(UUID id,
                   Establishment establishment,
                   String productDescription,
                   LocalDate buyDate,
                   CardNumber cardNumber,
                   BigDecimal amount) {
        this.id = id;
        this.establishment = establishment;
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public void addPaymentStrategy(final CardType cardType) {
        this.paymentStrategy = PaymentStrategy.getStrategy(cardType);
    }
}
