package br.com.alelo.consumer.consumerpat.entity.consumer;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Long number;
    private String city;
    private String country;
    private Long portalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consumer")
    private Consumer consumer;
}
