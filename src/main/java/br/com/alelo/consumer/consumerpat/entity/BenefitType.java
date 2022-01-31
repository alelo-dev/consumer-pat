package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="benefittype")
public class BenefitType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int benefitType_id;
    String typeDescription;

}
