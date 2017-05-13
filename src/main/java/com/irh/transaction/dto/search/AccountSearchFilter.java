package com.irh.transaction.dto.search;


/**
 * The filter for account searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class AccountSearchFilter extends NamedEntitySearchFilter{

    /**
     * The value of enabled to filter.
     */
    private Boolean enabled;

    /**
     * The permission code to filter.
     */
    private String permissionCode;

    /**
     * Gets the enabled.
     *
     * @return the enabled
     */
    public Boolean getEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     */
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }

    /**
     * Gets the permissionCode.
     *
     * @return the permissionCode.
     */
    public String getPermissionCode(){
        return permissionCode;
    }

    /**
     * Sets the permissionCode.
     *
     * @param permissionCode the permissionCode to set.
     */
    public void setPermissionCode(String permissionCode){
        this.permissionCode = permissionCode;
    }
}
