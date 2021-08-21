package br.com.alelo.consumer.consumerpat.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(length = 16, nullable = false)
    private String foodCard;

    @Column(length = 10, precision = 2, nullable = false)
    private Double foodCardBalance;

    @Column(length = 16, nullable = false)
    private String fuelCard;

    @Column(length = 10, precision = 2, nullable = false)
    private Double fuelCardBalance;

    @Column(length = 16, nullable = false)
    private String drugstoreCard;

    @Column(length = 10, precision = 2, nullable = false)
    private Double drugstoreCardBalance;

    @OneToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private ConsumerEntity consumer;
}
