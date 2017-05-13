package com.irh.transaction.model.branch;

import com.irh.transaction.model.common.IdentifiableEntity;

/**
 * Represents a branch table.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class BranchTable extends IdentifiableEntity{

    /**
     * The branch id.
     */
    private long branchId;

    /**
     * The code.
     */
    private String code;

    private String zone;

    /**
     * The parent table id.
     */
    private Long parentId;

    /**
     * The value indicates if the table is enabled.
     */
    private boolean enabled;

    /**
     * Gets the branchId.
     *
     * @return the branchId.
     */
    public long getBranchId(){
        return branchId;
    }

    /**
     * Sets the branchId.
     *
     * @param branchId the branchId to set.
     */
    public void setBranchId(long branchId){
        this.branchId = branchId;
    }

    /**
     * Gets the code.
     *
     * @return the code.
     */
    public String getCode(){
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set.
     */
    public void setCode(String code){
        this.code = code;
    }

    /**
     * Gets the parentId.
     *
     * @return the parentId.
     */
    public Long getParentId(){
        return parentId;
    }

    /**
     * Sets the parentId.
     *
     * @param parentId the parentId to set.
     */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    /**
     * Gets the enabled.
     *
     * @return the enabled.
     */
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public String getZone(){
        return zone;
    }

    public void setZone(String zone){
        this.zone = zone;
    }
}
