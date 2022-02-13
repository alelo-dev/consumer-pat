package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cards"})
@EqualsAndHashCode(exclude = {"cards"})
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String establishmentName;
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "cardId", referencedColumnName = "id")
    private Card card;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBuy;
    private Double value;

}
