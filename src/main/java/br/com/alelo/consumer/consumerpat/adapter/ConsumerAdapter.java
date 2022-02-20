package br.com.alelo.consumer.consumerpat.adapter;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.vo.ConsumerVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumerAdapter {



    public static ConsumerVo modelToVo(Consumer consumer){

        return ConsumerVo.builder().contacts(ContactAdapter.modelToVo(consumer.getContacts()))
                 .address(AddressAdapter.modelToVo(consumer.getAddresses()))
                 .id(consumer.getId())
                 .birthDate(consumer.getBirthDate())
                 .documentNumber(consumer.getDocumentNumber())
                 .name(consumer.getName()).build();
    }

    public static List<ConsumerVo> modelToVo(List<Consumer> consumers){
        if (CollectionUtils.isEmpty(consumers)) {
            return new ArrayList<>();
        }
        return consumers.stream().map(ConsumerAdapter::modelToVo).collect(Collectors.toList());
    }

    public static Consumer voToModel(ConsumerVo consumer){

        return Consumer.builder().contacts(ContactAdapter.voToModel(consumer.getContacts()))
                .addresses(AddressAdapter.voToModel(consumer.getAddress()))
                .id(consumer.getId())
                .birthDate(consumer.getBirthDate())
                .documentNumber(consumer.getDocumentNumber())
                .name(consumer.getName()).build();
    }

    public static List<Consumer> voToModel(List<ConsumerVo> consumers){
        if (CollectionUtils.isEmpty(consumers)) {
            return new ArrayList<>();
        }
        return consumers.stream().map(ConsumerAdapter::voToModel).collect(Collectors.toList());
    }
}
