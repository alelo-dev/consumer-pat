package br.com.alelo.consumer.consumerpat.pagination.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Métodos utilitários para paginação.
 */
public class PaginatorUtils {

    private PaginatorUtils() {
    }

    public static String currentPageToOffset(final int currentPage, final int pageSize) {
        return String.valueOf((currentPage - 1) * pageSize);
    }

    public static String currentPageToOffset(final String currentPage, final String pageSize) {
        return currentPageToOffset(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
    }

    public static String pageSizeToLimit(final int currentPage, final int pageSize) {
        return String.valueOf(((currentPage - 1) * pageSize) + pageSize);
    }

    public static String pageSizeToLimit(final String currentPage, final String pageSize) {
        return pageSizeToLimit(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
    }

    public static Pageable getPageable(final int startPosition, final int maxResults,
                                       final String sortField, final String sortDirection) {
        final Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        final Sort sort =  Sort.by(direction, sortField);

        return PageRequest.of(startPosition, maxResults, sort);
    }
}
