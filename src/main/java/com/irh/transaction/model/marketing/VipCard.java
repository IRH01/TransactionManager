package com.irh.transaction.model.marketing;

import com.irh.transaction.model.branch.Branch;
import com.irh.transaction.model.common.HeadquarterEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents a VIP card.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> A new {@link com.irh.transaction.model.marketing.VipCard#transactions} field to hold all types of
 * transaction records (recharge, consume and refund). </li> <li> New fields {@link com.irh.transaction.model.marketing.VipCard#point}, {@link
 * com.irh.transaction.model.marketing.VipCard#grade} have been added to support VIP grade system.</li><li> Added the {@link com.irh.transaction.model.marketing.VipCard#password}, {@link
 * com.irh.transaction.model.marketing.VipCard#createdAt} fields. </li> <li>Added the {@link com.irh.transaction.model.marketing.VipCard#birthDate}, {@link com.irh.transaction.model.marketing.VipCard#gender} and {@link
 * com.irh.transaction.model.marketing.VipCard#idCardNumber} fields. </li> </ol>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public class VipCard extends HeadquarterEntity{
    /**
     * The VIP card number.
     */
    private String number;

    /**
     * The card owner name.
     */
    private String name;

    /**
     * The card owner gender.
     *
     * @since 1.1
     */
    private String gender;

    /**
     * The birth date of the owner.
     *
     * @since 1.1
     */
    private Date birthDate;

    /**
     * The identity card number of the owner.
     *
     * @since 1.1
     */
    private String idCardNumber;

    /**
     * The mobile.
     */
    private String mobile;

    /**
     * The password.
     *
     * @since 1.1
     */
    private String password;

    /**
     * The branch where the member was initialized.
     */
    private Branch branch;

    /**
     * The balance.
     */
    private BigDecimal balance;

    /**
     * The amount of remaining invoice quota.
     */
    private BigDecimal invoiceQuota;

    /**
     * The status.
     */
    private com.irh.transaction.model.marketing.VipCardStatus status;

    /**
     * The list of transaction records.
     *
     * @since 1.1
     */
    private List<VipCardTransaction> transactions;

    /**
     * The point.
     *
     * @since 1.1
     */
    private int point;

    /**
     * The grade.
     *
     * @since 1.1
     */
    private int grade;

    /**
     * The datetime when the member was created.
     *
     * @since 1.1
     */
    private Date createdAt;

    /**
     * Gets the number.
     *
     * @return the number.
     */
    public String getNumber(){
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the number to set.
     */
    public void setNumber(String number){
        this.number = number;
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
     * Gets the gender.
     *
     * @return the gender.
     */
    public String getGender(){
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the gender to set.
     */
    public void setGender(String gender){
        this.gender = gender;
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
     * Gets the balance.
     *
     * @return the balance.
     */
    public BigDecimal getBalance(){
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance the balance to set.
     */
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public com.irh.transaction.model.marketing.VipCardStatus getStatus(){
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set.
     */
    public void setStatus(VipCardStatus status){
        this.status = status;
    }

    /**
     * Gets the transactions.
     *
     * @return the transactions.
     * @since 1.1
     */
    public List<VipCardTransaction> getTransactions(){
        return transactions;
    }

    /**
     * Sets the transactions.
     *
     * @param transactions the transactions to set.
     * @since 1.1
     */
    public void setTransactions(List<VipCardTransaction> transactions){
        this.transactions = transactions;
    }

    /**
     * Gets the point.
     *
     * @return the point.
     * @since 1.1
     */
    public int getPoint(){
        return point;
    }

    /**
     * Sets the point.
     *
     * @param point the point to set.
     * @since 1.1
     */
    public void setPoint(int point){
        this.point = point;
    }

    /**
     * Gets the grade.
     *
     * @return the grade.
     * @since 1.1
     */
    public int getGrade(){
        return grade;
    }

    /**
     * Sets the grade.
     *
     * @param grade the grade to set.
     * @since 1.1
     */
    public void setGrade(int grade){
        this.grade = grade;
    }

    /**
     * Gets the password.
     *
     * @return the password.
     * @since 1.1
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set.
     * @since 1.1
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Gets the createdAt.
     *
     * @return the createdAt.
     * @since 1.1
     */
    public Date getCreatedAt(){
        return createdAt;
    }

    /**
     * Sets the createdAt.
     *
     * @param createdAt the createdAt to set.
     * @since 1.1
     */
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    /**
     * Gets the invoiceQuota.
     *
     * @return the invoiceQuota.
     */
    public BigDecimal getInvoiceQuota(){
        return invoiceQuota;
    }

    /**
     * Sets the invoiceQuota.
     *
     * @param invoiceQuota the invoiceQuota to set.
     */
    public void setInvoiceQuota(BigDecimal invoiceQuota){
        this.invoiceQuota = invoiceQuota;
    }

    /**
     * Gets the birthDate.
     *
     * @return the birthDate.
     * @since 1.1
     */
    public Date getBirthDate(){
        return birthDate;
    }

    /**
     * Sets the birthDate.
     *
     * @param birthDate the birthDate to set.
     * @since 1.1
     */
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    /**
     * Gets the idCardNumber.
     *
     * @return the idCardNumber.
     * @since 1.1
     */
    public String getIdCardNumber(){
        return idCardNumber;
    }

    /**
     * Sets the idCardNumber.
     *
     * @param idCardNumber the idCardNumber to set.
     * @since 1.1
     */
    public void setIdCardNumber(String idCardNumber){
        this.idCardNumber = idCardNumber;
    }
}
