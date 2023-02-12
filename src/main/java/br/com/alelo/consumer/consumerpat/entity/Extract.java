package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @NonNull
    @Column
    @JsonIgnore
    private int establishmentNameId;

    @NonNull
    @Column
    private String establishmentName;

    @NonNull
    @Column
    private String productDescription;

    @NonNull
    @Column
    private LocalDateTime dateBuy;

    @NonNull
    @Column
    private long cardNumber;

    @NonNull
    @Column
    private BigDecimal amount;

}