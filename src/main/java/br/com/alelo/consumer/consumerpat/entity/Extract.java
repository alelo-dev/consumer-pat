package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long extractId;
    private String establishmentName;
    private Long establishmentType;
    private String productDescription;
    private double amount;

}
