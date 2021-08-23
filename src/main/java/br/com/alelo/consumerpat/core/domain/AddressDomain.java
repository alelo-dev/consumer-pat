package br.com.alelo.consumerpat.core.domain;

import br.com.alelo.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumerpat.core.exception.RequiredFieldsException;
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
public class AddressDomain {

    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;

    public void validateRequiredFields() throws RequiredFieldsException {
        Map<String, String> fieldErrors = new HashMap<>();

        if (this.street == null || this.street.equals("")) {
            fieldErrors.put("street", "invalid.item");
        }

        if (this.number == null) {
            fieldErrors.put("number", "invalid.item");
        }

        if (this.city == null || this.city.equals("")) {
            fieldErrors.put("city", "invalid.item");
        }

        if (this.country == null || this.country.equals("")) {
            fieldErrors.put("contact", "invalid.item");
        }

        if (this.postalCode == null || this.postalCode.equals("")) {
            fieldErrors.put("address", "invalid.item");
        }

        if (fieldErrors.size() > 0) {
            throw new RequiredFieldsException(fieldErrors);
        }
    }
}
