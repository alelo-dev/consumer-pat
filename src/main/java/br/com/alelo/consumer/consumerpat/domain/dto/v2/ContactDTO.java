package br.com.alelo.consumer.consumerpat.domain.dto.v2;

import br.com.alelo.consumer.consumerpat.domain.entity.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    private int id;
    private Long mobilePhoneNumber;
    private Long residencePhoneNumber;
    private Long phoneNumber;
    private String email;

    public static ContactDTO convertEntityToDto(ContactEntity contactEntity){
        if(contactEntity != null){
            return new ContactDTO(
                    contactEntity.getId(),
                    contactEntity.getMobilePhoneNumber(),
                    contactEntity.getResidencePhoneNumber(),
                    contactEntity.getPhoneNumber(),
                    contactEntity.getEmail()
            );
        }
        return null;
    }

}
