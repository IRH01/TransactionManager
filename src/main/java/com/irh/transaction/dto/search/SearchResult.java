package com.irh.transaction.dto.search;

import java.util.List;

/**
 * Represents a result for entity searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @param <T> the type of the entity to search.
 * @author Iritchie.ren
 * @version 1.1
 */
public class SearchResult<T>{

    /**
     * The current page.
     */
    private Integer page;

    /**
     * The page size.
     */
    private Integer size;

    /**
     * The total count of items.
     */
    private long count;

    /**
     * The number of total pages.
     */
    private int totalPages;

    /**
     * The list of items in the current page.
     */
    private List<T> items;

    /**
     * Gets the current page.
     *
     * @return the current page
     */
    public Integer getPage(){
        return page;
    }

    /**
     * Sets the current page.
     *
     * @param page the current page to set.
     */
    public void setPage(Integer page){
        this.page = page;
    }

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public Integer getSize(){
        return size;
    }

    /**
     * Sets the page size.
     *
     * @param size the page size to set.
     */
    public void setSize(Integer size){
        this.size = size;
    }

    /**
     * Gets the total count of items.
     *
     * @return the total count of items
     */
    public long getCount(){
        return count;
    }

    /**
     * Sets the total count of items.
     *
     * @param count the total count of items to set.
     */
    public void setCount(long count){
        this.count = count;
    }

    /**
     * Gets the list of items in the current page.
     *
     * @return the list of items in the current page
     */
    public List<T> getItems(){
        return items;
    }

    /**
     * Sets the list of items in the current page.
     *
     * @param items the list of items in the current page to set.
     */
    public void setItems(List<T> items){
        this.items = items;
    }

    /**
     * Gets the number of total pages.
     *
     * @return the number of total pages
     */
    public int getTotalPages(){
        return totalPages;
    }

    /**
     * Sets the number of total pages.
     *
     * @param totalPages the number of total pages to set.
     */
    public void setTotalPages(int totalPages){
        this.totalPages = totalPages;
    }
}
