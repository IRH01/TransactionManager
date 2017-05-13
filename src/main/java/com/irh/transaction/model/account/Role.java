package com.irh.transaction.model.account;

import com.irh.transaction.model.common.HeadquarterEntity;

import java.util.List;

/**
 * Represents a role.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br> Added the {@link com.irh.transaction.model.account.Role#level} field. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class Role extends HeadquarterEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The role level.
     *
     * @since 1.1
     */
    private com.irh.transaction.model.account.RoleLevel level;

    /**
     * The list of permissions.
     */
    private List<Permission> permissions;

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the level.
     *
     * @return the level.
     * @since 1.1
     */
    public com.irh.transaction.model.account.RoleLevel getLevel(){
        return level;
    }

    /**
     * Sets the level.
     *
     * @param level the level to set.
     * @since 1.1
     */
    public void setLevel(RoleLevel level){
        this.level = level;
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions.
     */
    public List<Permission> getPermissions(){
        return permissions;
    }

    /**
     * Sets the permissions.
     *
     * @param permissions the permissions to set.
     */
    public void setPermissions(List<Permission> permissions){
        this.permissions = permissions;
    }
}
