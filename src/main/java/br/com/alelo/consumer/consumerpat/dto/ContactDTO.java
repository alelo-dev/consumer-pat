package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Integer id;

    private String mobilePhoneNumber;

    private String residencePhoneNumber;

    @NonNull
    private String phoneNumber;

    private String email;
}
