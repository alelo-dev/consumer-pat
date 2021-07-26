package br.com.alelo.consumer.consumerpat.model.dto.consumer;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 13:46
 */

import br.com.alelo.consumer.consumerpat.model.enums.CountryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ConsumerAddressDTO {

    private Integer id;
    private String street;
    private String number;
    private String city;
    private CountryEnum country;
    private Integer portalCode;
    private LocalDateTime createdAt;
    
}
