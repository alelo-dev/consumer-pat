package br.com.alelo.consumer.consumerpat.controller.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class ConsumerSaveDto {
    
    String    name;
    String    documentNumber;
    LocalDate birthDate;
    
}
