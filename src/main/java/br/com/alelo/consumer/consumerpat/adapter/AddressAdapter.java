package br.com.alelo.consumer.consumerpat.adapter;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.consumer.Address;
import br.com.alelo.consumer.consumerpat.vo.AdddresVo;
import br.com.alelo.consumer.consumerpat.vo.ExtractVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressAdapter {


    public static AdddresVo modelToVo(Address adddres){

        return AdddresVo.builder().number(adddres.getNumber())
                        .country(adddres.getCountry())
                        .portalCode(adddres.getPortalCode())
                        .street(adddres.getStreet())
                        .city(adddres.getCity())
                        .id(adddres.getId())
                        .build();
    }

    public static List<AdddresVo> modelToVo(List<Address> addresses){
        if (CollectionUtils.isEmpty(addresses)) {
            return new ArrayList<>();
        }
        return addresses.stream().map(AddressAdapter::modelToVo).collect(Collectors.toList());
    }

    public static Address voToModel(AdddresVo adddres){

        return Address.builder().number(adddres.getNumber())
                .country(adddres.getCountry())
                .portalCode(adddres.getPortalCode())
                .street(adddres.getStreet())
                .city(adddres.getCity())
                .id(adddres.getId())
                .build();
    }

    public static List<Address> voToModel(List<AdddresVo> addresses){
        if (CollectionUtils.isEmpty(addresses)) {
            return new ArrayList<>();
        }
        return addresses.stream().map(AddressAdapter::voToModel).collect(Collectors.toList());
    }

}
