package br.com.alelo.consumer.consumerpat.web.vo.extract;

import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.web.vo.pagination.BaseFilterVO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExtractFilterVO extends BaseFilterVO implements Serializable {

    public enum FieldsToSort {
        ID("id"),
        ESTABLISHMENT_ID("establishmentId"),
        ESTABLISHMENT_NAME("establishmentName"),
        PRODUCT_DESCRIPTION("productDescription"),
        DATE_BUY("dateBuy"),
        VALUE("value"),
        CARD_NUMBER("cardNumber");

        private String value;

        FieldsToSort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private Long establishmentId;

    private String establishmentName;

    private String productDescription;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate dateBuyStart;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN)
    private LocalDate dateBuyEnd;

    private BigDecimal value;

    private Long cardNumber;

    private FieldsToSort sortField = FieldsToSort.ID;

    public LocalDate getDateBuyEnd() {
        if (dateBuyEnd != null) {
           return dateBuyEnd.plusDays(1);
        }
        return null;
    }
}
