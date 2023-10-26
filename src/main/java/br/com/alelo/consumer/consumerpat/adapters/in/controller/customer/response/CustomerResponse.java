package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private UUID id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    private AddressResponse address;
    private ContactResponse contact;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
