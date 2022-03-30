package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MOBILE_PHONE_NUMBER", length = LengthFieldsBD.LENGTH_11)
    private Integer mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER", length = LengthFieldsBD.LENGTH_10)
    private Integer residencePhoneNumber;

    @Column(name = "PHONE_NUMBER", nullable = false, length = LengthFieldsBD.LENGTH_11)
    private Integer phoneNumber;

    @Column(name = "EMAIL", length = LengthFieldsBD.LENGTH_50)
    private String email;

    @OneToOne
    @JoinColumn(name = "CONSUMER_ID", nullable = false)
    private Consumer consumer;
}
