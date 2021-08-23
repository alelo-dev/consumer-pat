package br.com.alelo.consumer.consumerpat.core.domain;

import br.com.alelo.consumer.consumerpat.core.exception.RequiredFieldsException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDomain {

    private Long id;
    private String consumerCode;
    private String name;
    private String document;
    private LocalDate birthDate;
    private ContactDomain contact;
    private AddressDomain address;
    private Set<CardDomain> cards;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public String generateConsumerCode() {
        this.consumerCode = UUID.randomUUID().toString();

        return this.consumerCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAllCardNumber() {
        if (this.cards == null) {
            return null;
        }

        return this.cards.stream().map(CardDomain::getCard).collect(Collectors.toList());
    }

    public void validateRequiredFields() throws RequiredFieldsException {
        Map<String, String> fieldErrors = new HashMap<>();

        if (this.name == null || this.name.equals("")) {
            fieldErrors.put("name", "invalid.item");
        }

        if (this.document == null || this.document.equals("")) {
            fieldErrors.put("document", "invalid.item");
        }

        if (this.birthDate == null) {
            fieldErrors.put("birth date", "invalid.item");
        }

        if (this.contact == null) {
            fieldErrors.put("contact", "invalid.item");
        }

        if (this.address == null) {
            fieldErrors.put("address", "invalid.item");
        }

        if (this.cards == null) {
            fieldErrors.put("card", "invalid.item");
        }

        if (fieldErrors.size() > 0) {
            throw new RequiredFieldsException(fieldErrors);
        }

        this.address.validateRequiredFields();
        this.contact.validateRequiredFields();

        for (CardDomain card : this.cards) {
            card.validateRequiredFields();
        }
    }
}
