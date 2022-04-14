package br.com.alelo.consumer.consumerpat.dto.request;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@Data
@Entity
@EqualsAndHashCode
public class ConsumerContactRequestDto {

    @NotNull
    Integer mobilePhoneNumber;
    @NotNull
    Integer residencePhoneNumber;
    @NotNull
    Integer workPhoneNumber;

    String email;
}
