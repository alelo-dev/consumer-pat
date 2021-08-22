package br.com.alelo.consumerpat.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDomain {

    private String mobilePhone;
    private String residencePhone;
    private String email;
}
