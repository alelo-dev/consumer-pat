package br.com.alelo.consumer.consumerpat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extract {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ESTABLISHMENT_TYPE")
    private Integer establishmentType;
    @Column(name = "OPERATION_TYPE")
    private Integer operationType;
    @Column(name = "ESTABLISHMENT_NAME")
    private String establishmentName;
    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;
    @Column(name = "DATE_OPERATION")
    private LocalDateTime dateOperation;
    @Column(name = "CARD_NUMBER")
    private String cardNumber;
    @Column(name = "VALUE")
    private Double value;
}
