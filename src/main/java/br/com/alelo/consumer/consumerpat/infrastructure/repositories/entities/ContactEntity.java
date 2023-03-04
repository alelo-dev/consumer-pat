package br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities;

import br.com.alelo.consumer.consumerpat.domain.entities.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "contact")
@NoArgsConstructor
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;
    @Column(name = "residence_phone_number")
    private String residencePhoneNumber;
    @Column(name = "work_phone_number")
    private String workPhoneNumber;
    private String email;

    @OneToOne
    @JoinColumn(name = "consumer_id")
    private ConsumerEntity consumer;

    public Contact toModel() {
        Contact contact = new Contact();
        BeanUtils.copyProperties(this, contact);

        return contact;
    }
}
