package br.com.alelo.consumer.consumerpat.helper;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class UtilMocks {
	
	@SuppressWarnings("unchecked")
	public static <T> Page<T> page(Pageable page, T... objs) {
		final var list = Stream.of(objs)
			.limit(page.getPageSize())
			.collect(Collectors.toList());
		
		final var size = list.size() == objs.length ? list.size() 
				: objs.length; 
		
		return new PageImpl<T>(list, page, 
				(page.getPageNumber() * page.getPageSize()) + size);
	}

}
