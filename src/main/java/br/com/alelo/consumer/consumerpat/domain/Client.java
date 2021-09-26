package br.com.alelo.consumer.consumerpat.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"doc_number"})})
@Builder
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idt_client")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="doc_number", unique = true, nullable = false)
    private String documentNumber;

    @Column(name="birth_date")
    private Date birthDate;

    /* address */

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private int number;

    @Column(name = "city" )
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "portalCode")
    private int portalCode;

    /* contact */

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "residence_phone_number")
    private String residencePhoneNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

}
