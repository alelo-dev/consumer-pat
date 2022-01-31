package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="benefits")
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    @JoinColumn(name = "benefitType_id")
    BenefitType benefitType_id;

    @OneToMany
    @JoinColumn(name = "cardNumber")
    List<BenefitCard> cardNumber;


}
