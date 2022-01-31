package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="benefitcard")
public class BenefitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int benefitCard_id;


    int cardNumber;

    Long cardBalance;


}
