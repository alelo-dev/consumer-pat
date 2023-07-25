package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Consumer {

    private UUID id;
    @NotBlank(message = "Nome is required")
    private String name;
    @NotBlank(message = "Document number is required")
    private String documentNumber;
    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;
    private Contact contact;
    private Address address;

    public Consumer(UUID id, String name, String documentNumber, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
    }

    public void changeConsumer(final Consumer updateConsumer) {
        this.name = updateConsumer.name;
        this.documentNumber = updateConsumer.documentNumber;
        this.birthDate = updateConsumer.birthDate;
    }

    public void addContact(final Contact contact) {
        if (contact == null) {
            throw new DomainException("Contact is required");
        }
        this.contact = contact;
    }

    public void addAddress(final Address address) {
        if (address == null) {
            throw new DomainException("Address is required");
        }
        this.address = address;
    }
}
