package com.irh.transaction.dto;

import javax.validation.constraints.NotNull;

/**
 * Contains login information.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class LoginDTO{

    /**
     * The credential id.
     */
    @NotNull
    private String credentialId;

    /**
     * The password.
     */
    @NotNull
    private String password;

    /**
     * The headquarter code.
     */
    @NotNull
    private String hqCode;

    /**
     * Gets the credentialId.
     *
     * @return the credentialId
     */
    public String getCredentialId() {
        return credentialId;
    }

    /**
     * Sets the credentialId.
     *
     * @param credentialId
     *         the credentialId to set.
     */
    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password
     *         the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the hqCode.
     *
     * @return the hqCode
     */
    public String getHqCode() {
        return hqCode;
    }

    /**
     * Sets the hqCode.
     *
     * @param hqCode
     *         the hqCode to set.
     */
    public void setHqCode(String hqCode) {
        this.hqCode = hqCode;
    }
}
