package br.com.alelo.consumer.consumerpat.application.core.domain.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    private Address address;

    private Contact contact;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    public Customer() {
    }

    public Customer(UUID id, String name, String documentNumber, LocalDate birthDate, Address address, Contact contact) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.contact = contact;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(documentNumber, customer.documentNumber)
                && Objects.equals(birthDate, customer.birthDate) && Objects.equals(address, customer.address) && Objects.equals(contact, customer.contact)
                && Objects.equals(createdAt, customer.createdAt) && Objects.equals(updatedAt, customer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, documentNumber, birthDate, address, contact, createdAt, updatedAt);
    }
}
