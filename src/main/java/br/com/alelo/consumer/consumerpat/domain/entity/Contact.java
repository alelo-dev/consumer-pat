package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;
    @OneToOne(mappedBy = "contact")
    private Consumer consumer;
}
