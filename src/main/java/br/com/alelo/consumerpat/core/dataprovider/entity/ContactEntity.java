package br.com.alelo.consumerpat.core.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false)
    private String mobilePhone;

    @Column(length = 8, nullable = false)
    private String residencePhone;

    @Column(length = 100, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private ConsumerEntity consumer;
}
