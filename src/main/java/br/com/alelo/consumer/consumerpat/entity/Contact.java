package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.AUTO;

/**
 * Representation of the consumer's contact
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
public class Contact {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    @JsonIgnore
    @OneToOne(mappedBy = "contact")
    private Consumer consumer;

}
