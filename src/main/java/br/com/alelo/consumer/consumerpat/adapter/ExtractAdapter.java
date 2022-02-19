package br.com.alelo.consumer.consumerpat.adapter;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.vo.CardVo;
import br.com.alelo.consumer.consumerpat.vo.ExtractVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtractAdapter {


    public static ExtractVo modelToVo(Extract extract){

        return ExtractVo.builder().dateBuy(extract.getDateBuy())
                .productDescription(extract.getProductDescription())
                .value(extract.getValue())
                .establishmentId(extract.getEstablishment().getId())
                .establishmentName(extract.getEstablishment().getName())
                .cardNumber(extract.getCard().getNumber()).build();
    }

    public static List<ExtractVo> modelToVo(List<Extract> extracts){
        if (CollectionUtils.isEmpty(extracts)) {
            return new ArrayList<>();
        }
        return extracts.stream().map(ExtractAdapter::modelToVo).collect(Collectors.toList());
    }

}
