package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class RegisterConsumerCommand {

    private String name;

    private int documentNumber;

    private LocalDate birthDate;

    @Valid
    private Set<NewContact> contacts;

    @Valid
    private Set<NewAddress> addresses;

    @Valid
    private Set<NewCard> cards;

    public static RegisterConsumerCommand of(NewConsumer consumer) {

        var command = new RegisterConsumerCommand();

        command.setName(consumer.getName());
        command.setDocumentNumber(consumer.getDocumentNumber());
        command.setBirthDate(consumer.getBirthDate());
        command.setAddresses(consumer.getAddresses());
        command.setCards(consumer.getCards());
        command.setContacts(consumer.getContacts());

        return command;
    }
}
