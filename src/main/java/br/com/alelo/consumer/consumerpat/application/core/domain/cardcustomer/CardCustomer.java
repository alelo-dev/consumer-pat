package br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer;

import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CardCustomer {
    private UUID id;

    private String cardType;
    private String cardNumber;
    private BigDecimal cardBalance;

    private Customer customer;

    public CardCustomer() {
    }

    public CardCustomer(UUID id, String cardType, String cardNumber, BigDecimal cardBalance, Customer customer) {
        this.id = id;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCustomer that = (CardCustomer) o;
        return Objects.equals(id, that.id) && Objects.equals(cardType, that.cardType) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardBalance, that.cardBalance) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardType, cardNumber, cardBalance, customer);
    }
}
