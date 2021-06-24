package br.com.alelo.consumer.consumerpat.parameter;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@Data
@ApiModel("Transaction Parameter JSON")
public class TransactionParameter {

    @ApiModelProperty(required = true)
    private int consumerId;

    @ApiModelProperty(required = true)
    private CardType cardType;

    @ApiModelProperty(required = true)
    private String cardNumber;

    @ApiModelProperty(required = true)
    private BigDecimal transactionAmount;

    @ApiModelProperty(required = true)
    private String merchantName;

    @ApiModelProperty(required = true)
    private String productDescription;

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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
}