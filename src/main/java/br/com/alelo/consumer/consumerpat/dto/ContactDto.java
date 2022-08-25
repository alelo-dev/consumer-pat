package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactDto {

    private Long mobilePhoneNumber;
    
    private Long residencePhoneNumber;
    
    private Long phoneNumber;
    
    private String email;
}
