package br.com.alelo.consumer.consumerpat.infrastructure.repositories.wrappers;

import br.com.alelo.consumer.consumerpat.domain.entities.Extract;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ExtractEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class ExtractEntityWrapper {

    private ExtractEntity extractEntity;

    public ExtractEntityWrapper(ExtractEntity extractEntity) {
        this.extractEntity = extractEntity;
    }

    public ExtractEntity copyFromProperties(Extract extract) {
        BeanUtils.copyProperties(extract, extractEntity);
        return extractEntity;
    }
}
