package com.irh.transaction.model.branch;

import com.irh.transaction.model.common.HeadquarterEntity;

/**
 * Represents a branch.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>Added the {@link com.irh.transaction.model.branch.Branch#enabled} field. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class Branch extends HeadquarterEntity{

    /**
     * The branch group id.
     */
    private long groupId;

    /**
     * The name.
     */
    private String name;

    /**
     * The number
     */
    private int number;

    /**
     * The province.
     */
    private String province;

    /**
     * The city.
     */
    private String city;

    /**
     * The district;
     */
    private String district;

    /**
     * The address.
     */
    private String address;

    /**
     * The phone number.
     */
    private String phone;

    /**
     * The longitude of the geometry location.
     */
    private Double longitude;

    /**
     * The latitude of the geometry location.
     */
    private Double latitude;

    /**
     * The value indicates if the branch is enabled.
     *
     * @since 1.1
     */
    private boolean enabled;

    /**
     * Gets the groupId.
     *
     * @return the groupId.
     */
    public long getGroupId(){
        return groupId;
    }

    /**
     * Sets the groupId.
     *
     * @param groupId the groupId to set.
     */
    public void setGroupId(long groupId){
        this.groupId = groupId;
    }

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
     * Gets the district.
     *
     * @return the district.
     */
    public String getDistrict(){
        return district;
    }

    /**
     * Sets the district.
     *
     * @param district the district to set.
     */
    public void setDistrict(String district){
        this.district = district;
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

    /**
     * Gets the phone.
     *
     * @return the phone.
     */
    public String getPhone(){
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone the phone to set.
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude.
     */
    public Double getLongitude(){
        return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the longitude to set.
     */
    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    /**
     * Gets the latitude.
     *
     * @return the latitude.
     */
    public Double getLatitude(){
        return latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude the latitude to set.
     */
    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    /**
     * Gets the enabled.
     *
     * @return the enabled.
     * @since 1.1
     */
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     * @since 1.1
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    /**
     * Gets the enabled.
     *
     * @return the number.
     * @since 1.1
     */
    public Integer getNumber(){
        return number;
    }

    /**
     * Sets the enabled.
     *
     * @param number the enabled to set.
     * @since 1.1
     */
    public void setNumber(Integer number){
        this.number = number;
    }
}
