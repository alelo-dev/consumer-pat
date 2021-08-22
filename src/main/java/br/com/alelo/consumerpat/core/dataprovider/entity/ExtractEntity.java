package br.com.alelo.consumerpat.core.dataprovider.entity;

import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "extract")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extract_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String establishmentName;

    @Column(length = 100, nullable = false)
    private String productDescription;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateBuy;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private EstablishmentType establishmentType;

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    private CardEntity card;
}
