package br.com.alelo.consumer.consumerpat.web.vo.consumer;

import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.BaseFilterVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ConsumerFilterVO extends BaseFilterVO implements Serializable {

    public enum FieldsToSort {
        ID("id"),
        NAME("name"),
        DOCUMENT_NUMBER("documentNumber"),
        BIRTH_DATE("birthDate"),
        ADDRESS("address");

        private String value;

        FieldsToSort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private String name;

    private String documentNumber;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate birthDate;

    private FieldsToSort sortField = FieldsToSort.ID;

}
