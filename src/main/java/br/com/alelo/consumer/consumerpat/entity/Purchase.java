package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Integer establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private Double value;
}
