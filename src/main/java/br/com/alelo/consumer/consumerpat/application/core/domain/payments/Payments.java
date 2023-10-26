package br.com.alelo.consumer.consumerpat.application.core.domain.payments;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Payments {

    private UUID id;
    private Establishment establishment;
    private String productDescription;
    private LocalDate buyDate;
    private String cardNumber;
    private BigDecimal amount;

    public Payments() {
    }

    public Payments(UUID id, Establishment establishment, String productDescription, LocalDate buyDate, String cardNumber, BigDecimal amount) {
        this.id = id;
        this.establishment = establishment;
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payments payments = (Payments) o;
        return Objects.equals(id, payments.id) && Objects.equals(establishment, payments.establishment) && Objects.equals(productDescription, payments.productDescription) && Objects.equals(buyDate, payments.buyDate) && Objects.equals(cardNumber, payments.cardNumber) && Objects.equals(amount, payments.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, establishment, productDescription, buyDate, cardNumber, amount);
    }
}
