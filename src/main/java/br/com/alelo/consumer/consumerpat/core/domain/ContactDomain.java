package br.com.alelo.consumer.consumerpat.core.domain;

import br.com.alelo.consumer.consumerpat.core.exception.RequiredFieldsException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDomain {

    private Long id;
    private String mobilePhone;
    private String residencePhone;
    private String email;
    private ConsumerDomain consumer;

    public void setConsumer(ConsumerDomain consumer) {
        this.consumer = consumer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void validateRequiredFields() throws RequiredFieldsException {
        Map<String, String> fieldErrors = new HashMap<>();

        if (this.mobilePhone == null || this.mobilePhone.equals("")) {
            fieldErrors.put("mobilePhone", "invalid.item");
        }

        if (this.residencePhone == null || this.residencePhone.equals("")) {
            fieldErrors.put("residencePhone", "invalid.item");
        }

        if (this.email == null || this.email.equals("")) {
            fieldErrors.put("email", "invalid.item");
        }

        if (fieldErrors.size() > 0) {
            throw new RequiredFieldsException(fieldErrors);
        }
    }
}
