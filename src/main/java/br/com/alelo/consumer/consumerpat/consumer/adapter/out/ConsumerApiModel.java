package br.com.alelo.consumer.consumerpat.consumer.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerApiModel {

    private Integer consumerId;
    private int documentNumber;
    private String name;
    private LocalDate birthDate;
}
