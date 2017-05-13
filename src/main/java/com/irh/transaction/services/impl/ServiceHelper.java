package com.irh.transaction.services.impl;

import com.irh.transaction.dto.search.SearchFilter;
import com.irh.transaction.dto.search.SearchResult;

import java.util.List;

/**
 * Contains helper members for this component.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
class ServiceHelper {
    /**
     * Prevents this class from being initialized.
     */
    private ServiceHelper() {
    }

    /**
     * Checks if the parameter value is null.
     *
     * @param value
     *         the parameter value to check.
     * @param name
     *         the parameter name.
     *
     * @throws IllegalArgumentException
     *         if the parameter value is null.
     */
    static void checkNotNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException("The " + name + " cannot be null.");
        }
    }

    /**
     * Checks if the string parameter value is null or empty.
     *
     * @param value
     *         the string parameter value to check.
     * @param name
     *         the parameter name.
     *
     * @throws IllegalArgumentException
     *         if the parameter value is null or empty.
     */
    static void checkString(String value, String name) {
        checkNotNull(value, name);
        if (value.trim().length() == 0) {
            throw new IllegalArgumentException("The " + name + " cannot be empty.");
        }
    }

    /**
     * Checks if the list is <code>null</code> or empty.
     *
     * @param list
     *         the list to check.
     * @param name
     *         the parameter name.
     * @param <T>
     *         the concrete type of the list item.
     *
     * @throws IllegalArgumentException
     *         if the list is <code>null</code> or empty.
     */
    static <T> void checkList(List<T> list, String name) {
        checkNotNull(list, name);
        if (list.size() == 0) {
            throw new IllegalArgumentException("The " + name + " cannot be null or empty.");
        }
    }

    /**
     * Checks if the {@link SearchFilter} is valid.
     *
     * @param filter
     *         the {@link SearchFilter} to check.
     * @param required
     *         the value indicates if the filter is required.
     *
     * @throws IllegalArgumentException
     *         if the <em>filter.page</em> is not positive or <em>filter.size</em> is not positive; if the
     *         <em>filter.page</em> is provided and the <em>filter.size</em> is null.
     */
    static void checkSearchFilter(SearchFilter filter, boolean required) {
        if (filter == null) {
            if (required) {
                throw new IllegalArgumentException("The filter cannot be null");
            }
            return;
        }
        if (required && filter.getHqId() == null) {
            throw new IllegalArgumentException("The filter.hqId cannot be null.");
        }
        if (filter.getPage() == null) {
            return;
        }
        if (filter.getPage() <= 0) {
            throw new IllegalArgumentException("The filter.page must be positive.");
        }
        checkNotNull(filter.getSize(), "filter.size");
        if (filter.getSize() <= 0) {
            throw new IllegalArgumentException("The filter.size must be positive.");
        }
    }

    /**
     * Creates a search result.
     *
     * @param items
     *         the list of retrieved items.
     * @param count
     *         the number of total items.
     * @param filter
     *         the search filter.
     * @param <T>
     *         the concrete type of the entity to search.
     *
     * @return the created search result.
     */
    static <T> SearchResult<T> createSearchResult(List<T> items, long count, SearchFilter filter) {
        SearchResult<T> result = new SearchResult<>();
        result.setItems(items);
        result.setCount(count);
        if (filter != null) {
            result.setPage(filter.getPage());
            result.setSize(filter.getSize());
        }
        if (filter != null && filter.getPage() != null) {
            result.setTotalPages(countTotalPage(result.getSize(), count));
        } else {
            result.setTotalPages(1);
        }

        return result;
    }

    /**
     * Counts the total pages of the search result.
     *
     * @param size
     *         the page size.
     * @param count
     *         the total count of items.
     */
    private static int countTotalPage(int size, long count) {
        return (int) (count % size == 0 ? count / size : count / size + 1);
    }
}