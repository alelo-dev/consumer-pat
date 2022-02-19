package br.com.alelo.consumer.consumerpat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerVo {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    private String name;
    private Long documentNumber;
    private LocalDate birthDate;

    private List<AdddresVo> address;
    private List<ContactVo> contacts;


}
