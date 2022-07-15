package br.com.alelo.consumer.consumerpat.web.vo.consumer;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.web.vo.contact.ContactVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.ExtractVO;
import br.com.alelo.consumer.consumerpat.web.vo.address.AddressVO;
import br.com.alelo.consumer.consumerpat.web.vo.card.CardVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "name", "documentNumber", "birthDate", "address", "contacts", "cards", "extracts" })
public class ConsumerVO implements Serializable {

    private static final long serialVersionUID = 5570856633046067283L;

    private Long id;

    private String name;

    private String documentNumber;

    @JsonFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate birthDate;

    private AddressVO address;

    private Set<ContactVO> contacts = new LinkedHashSet<>();

    private Set<CardVO> cards = new LinkedHashSet<>();

    private Set<ExtractVO> extracts = new LinkedHashSet<>();

    public static ConsumerVO from(Consumer consumer) {
        return ConsumerVO.builder()
            .id(consumer.getId())
            .name(consumer.getName())
            .documentNumber(consumer.getDocumentNumber())
            .birthDate(consumer.getBirthDate())
            .address(Optional.ofNullable(consumer.getAddress())
                .map(AddressVO::from)
                .orElseGet(() -> null))
            .contacts(Optional.ofNullable(consumer.getContacts())
                .map(contacts -> {
                    return contacts.stream()
                        .map(ContactVO::from)
                        .collect(Collectors.toSet());
                })
                .orElse(Set.of()))
            .cards(Optional.ofNullable(consumer.getCards())
                .map(cards -> {
                    return cards.stream()
                        .map(CardVO::from)
                        .collect(Collectors.toSet());
                })
                .orElse(Set.of()))
            .extracts(Optional.ofNullable(consumer.getExtracts())
                .map(extracts -> {
                    return extracts.stream()
                        .map(ExtractVO::from)
                        .collect(Collectors.toSet());
                })
                .orElse(Set.of()))
            .build();
    }

}