package br.com.alelo.consumer.consumerpat.payment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Establishment {

    private Integer establishmentId;
    private String name;
}
