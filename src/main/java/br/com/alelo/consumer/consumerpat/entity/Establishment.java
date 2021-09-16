package br.com.alelo.consumer.consumerpat.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Establishment {
    
    private enum TYPE_ESTABLISHMENT { FOOD , FUEL , DRUGS , OTHER };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty
    private String name;

    @NotNull
    private TYPE_ESTABLISHMENT typeEstablishment;

}