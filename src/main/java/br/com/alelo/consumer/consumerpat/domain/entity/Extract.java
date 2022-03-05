package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Extract {

    @Id
    private Integer id;
    private Integer establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private Integer cardNumber;
    private Double value;
}
