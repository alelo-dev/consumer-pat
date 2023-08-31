package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="TB_CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    @OneToOne(mappedBy = "contact")
    private Consumer consumer;

}
