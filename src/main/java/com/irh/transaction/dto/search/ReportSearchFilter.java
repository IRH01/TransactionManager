package com.irh.transaction.dto.search;

/**
 * The filter for finance report searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class ReportSearchFilter extends DateFilter{

    /**
     * The value indicates the reports should be grouped by branch.
     */
    private boolean groupByBranch;

    /**
     * Gets the groupByBranch.
     *
     * @return the groupByBranch.
     */
    public boolean isGroupByBranch(){
        return groupByBranch;
    }

    /**
     * Sets the groupByBranch.
     *
     * @param groupByBranch the groupByBranch to set.
     */
    public void setGroupByBranch(boolean groupByBranch){
        this.groupByBranch = groupByBranch;
    }
}
