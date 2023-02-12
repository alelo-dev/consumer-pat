package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class TransactionDayDto {

    @NonNull
    public LocalDate date;

    @NonNull
    public long count;

}
