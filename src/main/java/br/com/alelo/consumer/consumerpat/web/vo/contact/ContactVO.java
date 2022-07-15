package br.com.alelo.consumer.consumerpat.web.vo.contact;

import br.com.alelo.consumer.consumerpat.model.entity.Contact;
import br.com.alelo.consumer.consumerpat.model.enums.ContactType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "value", "type" })
public class ContactVO implements Serializable {

    private Long id;

    private String value;

    private ContactType type;

    public static ContactVO from(Contact contact) {
        return ContactVO.builder()
            .id(contact.getId())
            .value(contact.getValue())
            .type(contact.getType())
            .build();
    }
}
