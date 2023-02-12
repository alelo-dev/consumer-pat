package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {

    @NotBlank
    private String user;

    @NotBlank
    private String password;

}