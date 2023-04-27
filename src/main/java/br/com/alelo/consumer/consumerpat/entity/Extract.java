package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column
    Integer establishmentNameId;
    @Column
    String establishmentName;
    @Column
    String productDescription;
    @Column
    Date dateBuy;
    @Column
    Long cardNumber;
    @Column
    Double amount;

}
