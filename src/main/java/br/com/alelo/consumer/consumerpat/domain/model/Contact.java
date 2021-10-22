package br.com.alelo.consumer.consumerpat.domain.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Contact {

    private String email;
    private String cellPhone;
    private String phone;
}
