package com.irh.transaction.dto.search;


/**
 * Contains filter parameters and other criteria for entity search.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> This class is now not abstract any more. </li> <li> Added the {@link
 * SearchFilter#hqId}, {@link SearchFilter#groupId} and {@link SearchFilter#branchId}. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class SearchFilter{

    /**
     * The the headquarter id to filter.
     *
     * @since 1.1
     */
    private Long hqId;

    /**
     * The branch group id to filter.
     *
     * @since 1.1
     */
    private Long groupId;

    /**
     * The branch id to filter.
     *
     * @since 1.1
     */
    private Long branchId;

    /**
     * The page to retrieve.
     */
    private Integer page;

    /**
     * The page size.
     */
    private Integer size;

    /**
     * The field to sort.
     */
    private String sortBy;

    /**
     * The sort order.
     */
    private SortOrder sortOrder;

    /**
     * Gets the hqId.
     *
     * @return the hqId.
     * @since 1.1
     */
    public Long getHqId(){
        return hqId;
    }

    /**
     * Sets the hqId.
     *
     * @param hqId the hqId to set.
     * @since 1.1
     */
    public void setHqId(Long hqId){
        this.hqId = hqId;
    }

    /**
     * Gets the groupId.
     *
     * @return the groupId.
     * @since 1.1
     */
    public Long getGroupId(){
        return groupId;
    }

    /**
     * Sets the groupId.
     *
     * @param groupId the groupId to set.
     * @since 1.1
     */
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }

    /**
     * Gets the branchId.
     *
     * @return the branchId.
     * @since 1.1
     */
    public Long getBranchId(){
        return branchId;
    }

    /**
     * Sets the branchId.
     *
     * @param branchId the branchId to set.
     * @since 1.1
     */
    public void setBranchId(Long branchId){
        this.branchId = branchId;
    }

    /**
     * Gets the page to retrieve.
     *
     * @return the page to retrieve.
     */
    public Integer getPage(){
        return page;
    }

    /**
     * Sets the page to retrieve.
     *
     * @param page the page to retrieve.
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
     * Gets the field to sort.
     *
     * @return the field to sort
     */
    public String getSortBy(){
        return sortBy;
    }

    /**
     * Sets the field to sort.
     *
     * @param sortBy the field to sort to set.
     */
    public void setSortBy(String sortBy){
        this.sortBy = sortBy;
    }

    /**
     * Gets the sort order.
     *
     * @return the sort order.
     */
    public SortOrder getSortOrder(){
        return sortOrder;
    }

    /**
     * Sets the sort order.
     *
     * @param sortOrder the sort order to set.
     */
    public void setSortOrder(SortOrder sortOrder){
        this.sortOrder = sortOrder;
    }
}