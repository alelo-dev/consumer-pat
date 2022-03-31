package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.constants.LengthFieldsBD;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MOBILE_PHONE_NUMBER", length = LengthFieldsBD.LENGTH_15)
    private String mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER", length = LengthFieldsBD.LENGTH_15)
    private String residencePhoneNumber;

    @Column(name = "PHONE_NUMBER", nullable = false, length = LengthFieldsBD.LENGTH_15)
    private String phoneNumber;

    @Column(name = "EMAIL", length = LengthFieldsBD.LENGTH_50)
    private String email;

    @OneToOne
    @JoinColumn(name = "CONSUMER_ID", nullable = false)
    private Consumer consumer;
}
