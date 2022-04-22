package br.com.alelo.consumer.consumerpat.respository.specifications;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.filters.ConsumerFilter;
import br.com.alelo.consumer.consumerpat.util.NumberUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class ConsumerSpecifications {

    public static Specification<Consumer> filter(ConsumerFilter filter) {
        return (root, query, builder) -> {

            var predicatesList = new ArrayList<Predicate>();

            if (NumberUtil.isPositive(filter.getId())) {
                predicatesList.add(builder.equal(root.get("id"), filter.getId()));
            }

            if (StringUtils.hasText(filter.getName())) {
                predicatesList.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (filter.getBirthDateStart() != null) {
                predicatesList.add(builder.greaterThanOrEqualTo(root.get("birthDate"),
                        filter.getBirthDateStart()));
            }

            if (filter.getBirthDateEnd() != null) {
                predicatesList.add(builder.lessThanOrEqualTo(root.get("birthDate"),
                        filter.getBirthDateEnd()));
            }


            return builder.and(predicatesList.toArray(new Predicate[0]));
        };
    }
}
