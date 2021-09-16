package br.com.alelo.consumer.consumerpat.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@Builder
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private LocalDateTime dateBuy;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Card card;

    @NotNull
    private BigDecimal value;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Establishment establishment;

}
