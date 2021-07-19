package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contacts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ConsumerCreateDto {

    private String name;
    private String documentNumber;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private List<Contacts> contactsList;
    private List<Card> cardsList;
    private List<Address> addressList;

    public Consumer toDomainObject() {
        return Consumer.builder()
                .name(name)
                .documentNumber(documentNumber)
                .birthDate(birthDate)
                .contactsList(contactsList)
                .cardsList(cardsList)
                .addressList(addressList)
                .build();

    }

}
