package br.com.alelo.consumer.consumerpat.specs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.vo.params.ConsumerParamsVO;

public class ConsumerSpec {
	
	public static Specification<Consumer> byFilter(final ConsumerParamsVO filter, String fieldOrder) {
		Assert.notNull(filter, "O filtro não pode ser nulo!");

		return (Root<Consumer> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			query.orderBy(cb.asc(root.get(fieldOrder)));
			return cb.and(constructPredicates(filter, cb, root, query));
		};
	}

	private static Predicate[] constructPredicates(final ConsumerParamsVO filter, final CriteriaBuilder cb, final Root<Consumer> root, CriteriaQuery<?> query) {

		final List<Predicate> predicates = new ArrayList<>();
		
		Optional.ofNullable(filter.getId()).ifPresent(id -> predicates.add(cb.equal(root.get("id"), id)));
		
		return predicates.toArray(new Predicate[] {});
	}
	
}
