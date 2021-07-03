package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class ExtractCard {
    @EmbeddedId
    private ExtractCardId id;

    @ManyToOne
    @MapsId("extract_id")
    @JoinColumn(name = "extract_id")
    private Extract extract;

    @ManyToOne
    @MapsId("card_id")
    private Card card;
    
}
