package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "extractCode"))
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String extractCode;
    private Long establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private BigDecimal value;

    @OneToMany(mappedBy = "extract")
    Set<ExtractCard> extractCards;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "ExtractCard", joinColumns =
            {@JoinColumn(name = "extract_id")}, inverseJoinColumns =
            {@JoinColumn(name = "card_id")})
    private Set<Card> cards;

    @PrePersist
    public void initializeUUID() {
        if (extractCode == null) {
            extractCode = UUID.randomUUID().toString();
        }
    }

}
