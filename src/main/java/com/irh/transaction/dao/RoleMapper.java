package com.irh.transaction.dao;

import com.irh.transaction.model.account.Role;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Role} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>Added a {@link com.irh.transaction.dao.RoleMapper#checkByNameAndHqId(String, long)} method to check
 * duplicate role. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public interface RoleMapper{

    /**
     * Saves the role.
     *
     * @param role the role to save.
     */
    @Transactional
    void save(Role role);

    /**
     * Deletes the permissions of the role with the given id.
     *
     * @param roleId the id of the role.
     */
    @Transactional
    void deleteRolePermissions(long roleId);

    /**
     * Saves the permission of the role.
     *
     * @param roleId       the id of the role.
     * @param permissionId the id of the permission
     */
    @Transactional
    void saveRolePermission(@Param("roleId") long roleId, @Param("permissionId") long permissionId);

    /**
     * Updates the role.
     *
     * @param role the role to update.
     * @return the number of affected row.
     */
    @Transactional
    int update(Role role);

    /**
     * Finds the role by id.
     *
     * @param id the role id.
     * @return the retrieved role, null if not found.
     */
    Role findOne(long id);

    /**
     * Checks if the role with the given name and headquarter id exists.
     *
     * @param name the name.
     * @param hqId the headquarter id.
     * @return the role id, null if not found.
     * @since 1.1
     */
    Long checkByNameAndHqId(@Param("name") String name, @Param("hqId") long hqId);

    /**
     * Searches the roles that match the filter.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Role> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of roles that match the filter.
     *
     * @param filter the filter.
     * @return the number of roles that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);
}
