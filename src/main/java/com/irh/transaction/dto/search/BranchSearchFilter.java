package com.irh.transaction.dto.search;


/**
 * The filter for branch searching.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since 1.1
 */
public class BranchSearchFilter extends NamedEntitySearchFilter{

    /**
     * The city to filter.
     */
    private String city;

    /**
     * The province to filter.
     */
    private String province;

    /**
     * The address to filter.
     */
    private String address;

    /**
     * Gets the city.
     *
     * @return the city.
     */
    public String getCity(){
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the city to set.
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Gets the province.
     *
     * @return the province.
     */
    public String getProvince(){
        return province;
    }

    /**
     * Sets the province.
     *
     * @param province the province to set.
     */
    public void setProvince(String province){
        this.province = province;
    }

    /**
     * Gets the address.
     *
     * @return the address.
     */
    public String getAddress(){
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the address to set.
     */
    public void setAddress(String address){
        this.address = address;
    }
}
