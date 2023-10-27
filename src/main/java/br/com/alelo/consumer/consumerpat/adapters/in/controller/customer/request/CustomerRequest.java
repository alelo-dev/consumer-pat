package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "document number is required")
    private String documentNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate birthDate;

    @Valid
    @NotNull(message = "address is required")
    private AddressRequest address;

    @Valid
    @NotNull(message = "contact is required")
    private ContactRequest contact;
}
