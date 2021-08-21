package br.com.alelo.consumer.consumerpat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDomain {

    private Long id;
    private String foodCard;
    private Double foodCardBalance;
    private String fuelCard;
    private Double fuelCardBalance;
    private String drugstoreCard;
    private Double drugstoreCardBalance;
}
