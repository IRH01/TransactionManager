package com.irh.transaction.model.common;

/**
 * Represents a headquarter.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b><br>Added the {@link com.irh.transaction.model.common.Headquarter#logo} field. </p>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class Headquarter extends IdentifiableEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The code.
     */
    private String code;

    /**
     * The logo URL.
     *
     * @since 1.1
     */
    private String logo;

    /**
     * The value indicates if the headquarter is enabled.
     *
     * @since 1.1
     */
    private boolean enabled;

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
     * Gets the code.
     *
     * @return the code.
     */
    public String getCode(){
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set.
     */
    public void setCode(String code){
        this.code = code;
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
     * Gets the logo.
     *
     * @return the logo.
     * @since 1.1
     */
    public String getLogo(){
        return logo;
    }

    /**
     * Sets the logo.
     *
     * @param logo the logo to set.
     * @since 1.1
     */
    public void setLogo(String logo){
        this.logo = logo;
    }
}
