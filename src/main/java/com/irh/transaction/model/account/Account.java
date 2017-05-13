package com.irh.transaction.model.account;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.branch.BranchGroup;
import com.irh.transaction.model.common.Headquarter;
import com.irh.transaction.model.common.HeadquarterEntity;

import java.util.Date;

/**
 * Represents an employee account.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class Account extends HeadquarterEntity{

    /**
     * The headquarter.
     */
    private Headquarter hq;

    /**
     * The credential id used for authentication.
     */
    private String credentialId;

    /**
     * The password.
     */
    private String password;

    /**
     * The fullname.
     */
    private String fullname;

    /**
     * The mobile.
     */
    private String mobile;

    /**
     * The role.
     */
    private Role role;

    /**
     * The branch group.
     */
    private BranchGroup branchGroup;

    /**
     * The branch.
     */
    private Branch branch;

    /**
     * The date when the account was created.
     */
    private Date createdAt;

    /**
     * The value indicates if the account is enabled.
     */
    private boolean enabled;

    /**
     * Gets the credentialId.
     *
     * @return the credentialId.
     */
    public String getCredentialId(){
        return credentialId;
    }

    /**
     * Sets the credentialId.
     *
     * @param credentialId the credentialId to set.
     */
    public void setCredentialId(String credentialId){
        this.credentialId = credentialId;
    }

    /**
     * Gets the password.
     *
     * @return the password.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Gets the fullname.
     *
     * @return the fullname.
     */
    public String getFullname(){
        return fullname;
    }

    /**
     * Sets the fullname.
     *
     * @param fullname the fullname to set.
     */
    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    /**
     * Gets the mobile.
     *
     * @return the mobile.
     */
    public String getMobile(){
        return mobile;
    }

    /**
     * Sets the mobile.
     *
     * @param mobile the mobile to set.
     */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    /**
     * Gets the role.
     *
     * @return the role.
     */
    public Role getRole(){
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the role to set.
     */
    public void setRole(Role role){
        this.role = role;
    }

    /**
     * Gets the branchGroup.
     *
     * @return the branchGroup.
     */
    public BranchGroup getBranchGroup(){
        return branchGroup;
    }

    /**
     * Sets the branchGroup.
     *
     * @param branchGroup the branchGroup to set.
     */
    public void setBranchGroup(BranchGroup branchGroup){
        this.branchGroup = branchGroup;
    }

    /**
     * Gets the branch.
     *
     * @return the branch.
     */
    public Branch getBranch(){
        return branch;
    }

    /**
     * Sets the branch.
     *
     * @param branch the branch to set.
     */
    public void setBranch(Branch branch){
        this.branch = branch;
    }

    /**
     * Gets the createdAt.
     *
     * @return the createdAt.
     */
    public Date getCreatedAt(){
        return createdAt;
    }

    /**
     * Sets the createdAt.
     *
     * @param createdAt the createdAt to set.
     */
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    /**
     * Gets the enabled.
     *
     * @return the enabled.
     */
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the enabled to set.
     */
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    /**
     * Gets the hq.
     *
     * @return the hq.
     */
    public Headquarter getHq(){
        return hq;
    }

    /**
     * Sets the hq.
     *
     * @param hq the hq to set.
     */
    public void setHq(Headquarter hq){
        this.hq = hq;
    }
}
