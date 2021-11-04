package br.com.alelo.consumer.consumerpat.entities;

import br.com.alelo.consumer.consumerpat.enumeraters.ESTABLISHMENT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EXTRACT")
public class Extract implements Serializable {

    private static final long serialVersionUID = -5174385727576087627L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated
    @Column(name = "ESTABLISHMENT_NAME_ID")
    private ESTABLISHMENT establishmentNameId;
    @Column(name = "ESTABLISHMENT_NAME")
    private String establishmentName;
    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;
    @Column(name = "DATE_BUY")
    private LocalDateTime dateBuy;
    @Column(name = "CARD_NUMBER")
    private Integer cardNumber;
    @Column(name = "VALUE")
    private BigDecimal value;

}
