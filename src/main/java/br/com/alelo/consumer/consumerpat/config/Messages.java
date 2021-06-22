package br.com.alelo.consumer.consumerpat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Value("${msg.consumer_not_found}")
    public String consumerNotFound;

    @Value("${msg.card_not_found}")
    public String cardNotFound;

    @Value("${msg.invalid_card_type}")
    public String invalidCardType;

    @Value("${msg.insufficient_balance}")
    public String insufficientBalance;

    @Value("${msg.value_toadd_mustbe_positive}")
    public String valueToAddMustBePositive;

    @Value("${msg.productvalue_mustbe_positive}")
    public String productValueMustBePositive;
}
