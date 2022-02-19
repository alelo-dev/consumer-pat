package br.com.alelo.consumer.consumerpat.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdddresVo {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String street;
    private Long number;
    private String city;
    private String country;
    private Long portalCode;




}
