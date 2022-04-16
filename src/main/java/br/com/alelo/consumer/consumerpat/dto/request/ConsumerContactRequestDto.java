package br.com.alelo.consumer.consumerpat.dto.request;


import com.sun.istack.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerContactRequestDto {

    @NotNull
    String mobilePhoneNumber;
    @NotNull
    String residencePhoneNumber;
    @NotNull
    String workPhoneNumber;

    String email;
}
