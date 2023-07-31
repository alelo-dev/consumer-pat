package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Payment {

    @JsonIgnore
    private UUID id;
    @Valid
    @NotNull(message = "establishment is required")
    private Establishment establishment;
    @NotBlank(message = "productDescription is required")
    private String productDescription;
    @NotNull(message = "buyDate is required")
    private LocalDate buyDate;
    @Valid
    @NotNull(message = "cardNumber is required")
    private CardNumber cardNumber;
    @NotNull(message = "amount is required")
    private BigDecimal amount;
    @JsonIgnore
    private PaymentStrategy paymentStrategy;

    public Payment(Establishment establishment,
                   String productDescription,
                   LocalDate buyDate,
                   CardNumber cardNumber,
                   BigDecimal amount) {
        this.establishment = establishment;
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public void addId(final UUID id) {
        this.id = id;
    }

    public void addPaymentStrategy(final CardType cardType) {
        this.paymentStrategy = PaymentStrategy.getStrategy(cardType);
    }
}
