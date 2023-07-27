package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
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

    public Consumer(String name, String documentNumber, LocalDate birthDate, Contact contact, Address address) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.contact = contact;
        this.address = address;
    }

    public void changeConsumer(final Consumer updateConsumer) {
        this.name = updateConsumer.getName();
        this.documentNumber = updateConsumer.getDocumentNumber();
        this.birthDate = updateConsumer.getBirthDate();
        this.addContact(updateConsumer.getContact());
        this.addAddress(updateConsumer.getAddress());
    }

    public void addId(final UUID id) {
        this.id = id;
    }

    public void addContact(final Contact contact) {
        if (contact != null) {
            this.contact = contact;
        }
    }

    public void addAddress(final Address address) {
        if (address != null) {
            this.address = address;
        }
    }
}
