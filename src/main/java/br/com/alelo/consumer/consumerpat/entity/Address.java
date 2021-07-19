package br.com.alelo.consumer.consumerpat.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;
    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private String postalCode;
}
