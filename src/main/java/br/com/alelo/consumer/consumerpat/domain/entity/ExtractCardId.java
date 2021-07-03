package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class ExtractCardId implements Serializable {

    @Column(name = "extract_id")
    private Long extractId;
    @Column(name = "card_id")
    private Long cardId;

}
