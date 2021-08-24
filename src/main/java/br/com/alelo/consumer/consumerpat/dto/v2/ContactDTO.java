package br.com.alelo.consumer.consumerpat.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.entity.ContactEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {

    // Usaria MapperStruct
    public ContactDTO(ContactEntity entity) {

        if (entity != null) {
            this.id = entity.getId();
            this.phones = entity.getPhones() != null
                    ? entity.getPhones().stream().map(PhoneDTO::new).collect(Collectors.toSet())
                    : null;
            this.email = entity.getEmail();
        }
    }

    private Integer id;
    private Set<PhoneDTO> phones;
    private String email;
}
