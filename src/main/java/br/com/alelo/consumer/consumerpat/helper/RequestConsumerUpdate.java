package br.com.alelo.consumer.consumerpat.helper;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Phone;
import lombok.Data;

@Data
public class RequestConsumerUpdate {
    
    @NotEmpty
    private String name;
    @NotEmpty
    private String documentNumber;
    @Email
    private String email;
    private LocalDateTime birthDate;
    private List<Phone> phones;
    private Address address;

}