package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentTypeEnum;
import lombok.*;

import javax.persistence.*;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long establishmentId;

    @Column
    private String name;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private int establishmentType;

        public Establishment(EstablishmentTypeEnum establishmentTypeEnum){
            this.establishmentType = establishmentTypeEnum.getEstablishmentType();
        }

}
