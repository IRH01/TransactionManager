package com.irh.transaction.dto.search;

import com.irh.transaction.model.common.Platform;

/**
 * The filter for category searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class CategorySearchFilter extends NamedEntitySearchFilter{

    /**
     * The platform to filter.
     */
    private Platform platform;

    /**
     * Gets the platform.
     *
     * @return the platform.
     */
    public Platform getPlatform(){
        return platform;
    }

    /**
     * Sets the platform.
     *
     * @param platform the platform to set.
     */
    public void setPlatform(Platform platform){
        this.platform = platform;
    }
}
