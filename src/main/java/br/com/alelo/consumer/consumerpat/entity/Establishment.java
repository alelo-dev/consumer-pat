package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private CardAndEstablishmentType type;

}
