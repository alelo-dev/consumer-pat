package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ErrorResponseDTO {

    private HttpStatus httpStatus;
    private List<ErrorDTO> errorList;
}
