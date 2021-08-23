package br.com.alelo.consumerpat.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 5, nullable = false)
    private Integer number;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 30, nullable = false)
    private String country;

    @Column(length = 8, nullable = false)
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private ConsumerEntity consumer;
}
