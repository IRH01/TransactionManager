package com.irh.transaction.model.statistic;

/**
 * Represents an data item for statistical details.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class StatisticDetailItem extends BaseSalesStatistic{

    /**
     * The grouping key.
     */
    private Object key;

    /**
     * Gets the key.
     *
     * @return the key.
     */
    public Object getKey(){
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the key to set.
     */
    public void setKey(Object key){
        this.key = key;
    }
}
