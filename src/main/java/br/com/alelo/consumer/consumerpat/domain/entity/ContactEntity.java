package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTACT")
public class ContactEntity {

    public ContactEntity(ContactDTO contactDTO) {
        if(contactDTO != null){
            this.id = contactDTO.getId();
            this.mobilePhoneNumber = contactDTO.getPhoneNumber();
            this.residencePhoneNumber = contactDTO.getResidencePhoneNumber();
            this.phoneNumber = contactDTO.getPhoneNumber();
            this.email = contactDTO.getEmail();
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    String email;
}
