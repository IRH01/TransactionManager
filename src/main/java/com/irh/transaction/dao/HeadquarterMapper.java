package com.irh.transaction.dao;

import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.dto.search.NamedEntitySearchFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents the mapper that provides operations to {@link Headquarter} in persistence.
 *
 * <p> <b>Thread Safety:</b> Implementation of this interface must be thread safe. </p>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public interface HeadquarterMapper{

    /**
     * Saves the headquarter.
     *
     * @param headquarter the headquarter to save.
     */
    @Transactional
    void save(Headquarter headquarter);

    /**
     * Updates the headquarter.
     *
     * @param headquarter the headquarter to update.
     * @return the number of affected row.
     */
    @Transactional
    int update(Headquarter headquarter);

    /**
     * Checks if the headquarter with the given code already exists.
     *
     * @param code the code to check.
     * @return the headquarter id, null if not found.
     */
    Long checkByCode(String code);

    /**
     * Searches headquarters.
     *
     * @param filter the search filter.
     * @return the search result.
     */
    List<Headquarter> search(@Param("filter") NamedEntitySearchFilter filter);

    /**
     * Counts the number of headquarters that match the filter.
     *
     * @param filter the filter.
     * @return the number of headquarters that match the filter.
     */
    long count(@Param("filter") NamedEntitySearchFilter filter);
}
