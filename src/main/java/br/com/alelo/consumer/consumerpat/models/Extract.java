package br.com.alelo.consumer.consumerpat.models;

import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column
    private EstablishmentEnum establishmentEnum;
    @Column
    private String establishmentName;
    @Column
    private String productDescription;
    @Column
    private Date dateBuy;
    @Column
    private int cardNumber;
    @Column
    private double value;

}
