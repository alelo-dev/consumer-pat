package br.com.alelo.consumer.consumerpat.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "extract")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extract_seq")
    @SequenceGenerator(name = "extract_seq", allocationSize = 1)
    private Long id;
    private Long establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", nullable = false,
            foreignKey = @ForeignKey(name = "extract_fk_01"))
    private Card card;
    private BigDecimal value;

    @Builder
    public Extract(Long id, Long establishmentNameId, String establishmentName, String productDescription, LocalDateTime dateBuy, Card card, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.card = card;
        this.value = value;
    }

    public Extract() {
    }
}
