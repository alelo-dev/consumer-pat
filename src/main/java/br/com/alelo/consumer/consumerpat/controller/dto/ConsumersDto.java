package br.com.alelo.consumer.consumerpat.controller.dto;

import java.util.List;

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
public class ConsumersDto {
    
    Long              total;
    Integer           size;
    Integer           page;
    List<ConsumerDto> items;
    
}
