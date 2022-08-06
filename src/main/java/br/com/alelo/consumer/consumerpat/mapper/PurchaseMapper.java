package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PurchaseMapper {

    public Extract toEntity(PurchaseRequest buyRequest, Card card) {
        Extract extract = new Extract();

        BeanUtils.copyProperties(buyRequest, extract);

        extract.setProducts(this.toServiceCodeEntity(buyRequest.getProducts()));
        extract.setCardNumber(card.getCardNumber());
        extract.setDateBuy(LocalDate.now());
        extract.setPurchaseCode(UUID.randomUUID().toString());

        return extract;
    }

    public List<String> toServiceCodeEntity(List<String> products) {
        if (CollectionUtils.isEmpty(products)) {
            throw new BusinessException("The product list is empty", HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return products
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

    public ExtractResponse toResponse(Extract extract) {

        ExtractResponse extractResponse = new ExtractResponse();

        BeanUtils.copyProperties(extract, extractResponse);


        return extractResponse;
    }
}
