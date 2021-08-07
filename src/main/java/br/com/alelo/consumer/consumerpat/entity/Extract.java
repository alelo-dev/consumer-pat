package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alelo.consumer.consumerpat.enuns.EstablishmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "extracts")
public class Extract {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productDescription;
    private Long establishmentId;
    private String establishmentName;
    private LocalDate dateBuy;
    private BigDecimal value;
    private String cardNumber;
    
    @Enumerated(EnumType.STRING)
    private EstablishmentTypeEnum establishmentType;
    
    

}
