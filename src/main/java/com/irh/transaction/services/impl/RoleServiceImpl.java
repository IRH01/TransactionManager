package com.irh.transaction.services.impl;

import com.irh.transaction.dao.RoleMapper;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import com.irh.transaction.dto.search.SearchResult;
import com.irh.transaction.model.account.Permission;
import com.irh.transaction.model.account.Role;
import com.irh.transaction.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of the {@link RoleService}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
@Service
public class RoleServiceImpl implements RoleService{

    /**
     * The mapper to manage {@link Role} in persistence.
     */
    @Autowired
    private RoleMapper mapper;

    /**
     * Saves the role.
     *
     * @param role the role to save.
     * @throws IllegalArgumentException if the account is null.
     * @throws EntityExistsException    if a role of the headquarter with the same name already exists.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void save(Role role) throws CoreServiceException{
        ServiceHelper.checkNotNull(role, "role");
        try{
            if(mapper.checkByNameAndHqId(role.getName(), role.getHqId()) != null){
                throw new EntityExistsException("A role with the same name already exists.");
            }
            mapper.save(role);
            if(role.getPermissions() != null){
                for(Permission permission : role.getPermissions()){
                    mapper.saveRolePermission(role.getId(), permission.getId());
                }
            }
        }catch(EntityExistsException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Updates the role.
     *
     * @param role the role to update.
     * @throws IllegalArgumentException if the account is null.
     * @throws EntityExistsException    if a role of the headquarter with the same name already exists.
     * @throws EntityNotFoundException  if the account cannot be found.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Transactional(rollbackFor = CoreServiceException.class)
    @Override
    public void update(Role role) throws CoreServiceException{
        ServiceHelper.checkNotNull(role, "role");
        try{
            Long duplicateId = mapper.checkByNameAndHqId(role.getName(), role.getHqId());
            if(duplicateId != null && role.getId() != duplicateId){
                throw new EntityExistsException("A role with the same name already exists.");
            }
            if(mapper.update(role) == 0){
                throw new EntityNotFoundException("The entity cannot be found.");
            }
            mapper.deleteRolePermissions(role.getId());
            if(role.getPermissions() != null){
                for(Permission permission : role.getPermissions()){
                    mapper.saveRolePermission(role.getId(), permission.getId());
                }
            }
        }catch(CoreServiceException ex){
            throw ex;
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Finds the role by id.
     *
     * @param id the role id.
     * @return the retrieved role, null if not found.
     * @throws CoreServiceException if any error occurs.
     */
    @Override
    public Role findOne(long id) throws CoreServiceException{
        try{
            return mapper.findOne(id);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }

    /**
     * Searches roles.
     *
     * @param filter the search filter.
     * @return the search result.
     * @throws IllegalArgumentException if the filter is null or if the <em>filter.hqId</em> is null; if the <em>filter.page</em> is not positive
     *                                  or <em>filter.size</em> is not positive; if the <em>filter.page</em> is provided and the
     *                                  <em>filter.size</em> is null.
     * @throws CoreServiceException     if any other error occurs.
     */
    @Override
    public SearchResult<Role> search(NamedEntitySearchFilter filter) throws CoreServiceException{
        ServiceHelper.checkSearchFilter(filter, true);
        try{
            List<Role> items = mapper.search(filter);
            long count = filter.getPage() != null ? mapper.count(filter) : items.size();
            return ServiceHelper.createSearchResult(items, count, filter);
        }catch(Exception ex){
            throw new CorePersistenceException("Error occurred while accessing the persistence.", ex);
        }
    }
}
