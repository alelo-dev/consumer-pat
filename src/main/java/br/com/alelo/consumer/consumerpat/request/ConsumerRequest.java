package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerRequest {
    private Integer id;
    private String name;
    @NotBlank
    @Pattern(regexp = "^(((\\d{3}).(\\d{3}).(\\d{3})-(\\d{2}))?((\\d{2}).(\\d{3}).(\\d{3})/(\\d{4})-(\\d{2}))?)*$", message = "CPF must be valid")
    private String documentNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthDate;
    private Contact contact;
    private Address address;
    private Set<Card> cards;
}
