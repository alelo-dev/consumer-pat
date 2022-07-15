package br.com.alelo.consumer.consumerpat.web.vo.consumer;

import br.com.alelo.consumer.consumerpat.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConsumerFormVO implements Serializable {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Document cannot be null")
    @CPF(message = "Document is not valid (CPF)")
    private String documentNumber;

    @NotNull(message = "BirthDate cannot be null")
    @JsonFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate birthDate;

}
