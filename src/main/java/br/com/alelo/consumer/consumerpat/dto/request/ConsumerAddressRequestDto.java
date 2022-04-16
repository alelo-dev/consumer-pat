package br.com.alelo.consumer.consumerpat.dto.request;


import com.sun.istack.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ConsumerAddressRequestDto {
    String street;

    @NotNull
    Integer number;

    String city;

    String country;

    @NotNull
    Integer postalCode;
}
