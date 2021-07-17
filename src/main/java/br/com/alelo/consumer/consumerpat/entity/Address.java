package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.AUTO;

/**
 * Representation of the consumer's address
 *
 * @author mcrj
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "consumer")
@ToString(exclude = "consumer")
public class Address {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Consumer consumer;
}
