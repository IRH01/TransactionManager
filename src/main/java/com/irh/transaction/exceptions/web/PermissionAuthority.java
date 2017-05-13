package com.irh.transaction.exceptions.web;

import org.springframework.security.core.GrantedAuthority;

/**
 * The authority representing the permission code of am employee account.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @see GrantedAuthority
 */
public class PermissionAuthority implements GrantedAuthority {

    /**
     * The permission code.
     */
    private final String permissionCode;

    /**
     * Creates a new instance of the {@link com.irh.transaction.exceptions.web.PermissionAuthority} class.
     *
     * @param permissionCode
     *         the permission code.
     */
    public PermissionAuthority(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * Gets the authority representing the permission code of an employee account.
     *
     * @return the authority representing the permission code of an employee account.
     */
    @Override
    public String getAuthority() {
        return permissionCode;
    }
}
