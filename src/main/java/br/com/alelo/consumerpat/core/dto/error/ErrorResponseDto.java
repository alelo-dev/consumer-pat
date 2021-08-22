package br.com.alelo.consumerpat.core.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class ErrorResponseDto {

    private List<ErrorMessageDto> errors;
}
