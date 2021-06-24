package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.parameter.TransactionParameter;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class StatementAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String merchantName;
    private String productDescription;
    private String cardNumber;
    private BigDecimal transactionAmount;

    @CreationTimestamp
    private LocalDateTime dateBuy;

    @ManyToOne
    private Consumer consumer;

    public static StatementAccount fromTransactionParameter(TransactionParameter parameter) {

        Consumer consumer = new Consumer();
        consumer.setId(parameter.getConsumerId());

        StatementAccount statementAccount = new StatementAccount();
        statementAccount.setConsumer(consumer);
        statementAccount.setCardNumber(parameter.getCardNumber());
        statementAccount.setTransactionAmount(parameter.getTransactionAmount());
        statementAccount.setMerchantName(parameter.getMerchantName());
        statementAccount.setProductDescription(parameter.getProductDescription());

        return statementAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalDateTime dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
