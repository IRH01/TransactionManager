package com.irh.transaction.model.product;

import com.irh.transaction.model.branch.PrintType;
import com.irh.transaction.model.common.HeadquarterEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a product.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ul> <li>The printTypeId has been replaced with the {@link com.irh.transaction.model.product.Product#printType}.
 * </li> <li> Added the {@link com.irh.transaction.model.product.Product#description} and {@link com.irh.transaction.model.product.Product#label} field. </li> </ul>
 *
 * @author  Iritchie.ren
 * @version 1.1
 */
public class Product extends HeadquarterEntity{

    /**
     * The name.
     */
    private String name;

    /**
     * The internationalized name.
     */
    private String interName;

    /**
     * The price.
     */
    private BigDecimal price;

    /**
     * The list of available optionGroups groups.
     */
    private List<com.irh.transaction.model.product.ProductOptionGroup> optionGroups;

    /**
     * The image URL.
     */
    private String imgUrl;

    /**
     * The print type.
     *
     * @since 1.1
     */
    private PrintType printType;

    /**
     * The status.
     */
    private ProductStatus status;

    /**
     * The description.
     *
     * @since 1.1
     */
    private String description;

    /**
     * The label.
     *
     * @since 1.1
     */
    private String label;

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
     * Gets the internationalized name.
     *
     * @return the internationalized name.
     */
    public String getInterName(){
        return interName;
    }

    /**
     * Sets the internationalized name.
     *
     * @param interName the internationalized name to set.
     */
    public void setInterName(String interName){
        this.interName = interName;
    }

    /**
     * Gets the price.
     *
     * @return the price.
     */
    public BigDecimal getPrice(){
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the price to set.
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    /**
     * Gets the optionGroups.
     *
     * @return the optionGroups.
     */
    public List<com.irh.transaction.model.product.ProductOptionGroup> getOptionGroups(){
        return optionGroups;
    }

    /**
     * Sets the optionGroups.
     *
     * @param optionGroups the optionGroups to set.
     */
    public void setOptionGroups(List<ProductOptionGroup> optionGroups){
        this.optionGroups = optionGroups;
    }

    /**
     * Gets the imgUrl.
     *
     * @return the imgUrl.
     */
    public String getImgUrl(){
        return imgUrl;
    }

    /**
     * Sets the imgUrl.
     *
     * @param imgUrl the imgUrl to set.
     */
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    /**
     * Gets the printType.
     *
     * @return the printType.
     * @since 1.1
     */
    public PrintType getPrintType(){
        return printType;
    }

    /**
     * Sets the printType.
     *
     * @param printType the printType to set.
     * @since 1.1
     */
    public void setPrintType(PrintType printType){
        this.printType = printType;
    }

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public ProductStatus getStatus(){
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set.
     */
    public void setStatus(ProductStatus status){
        this.status = status;
    }

    /**
     * Gets the description.
     *
     * @return the description.
     * @since 1.1
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set.
     * @since 1.1
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Gets the label.
     *
     * @return the label.
     * @since 1.1
     */
    public String getLabel(){
        return label;
    }

    /**
     * Sets the label.
     *
     * @param label the label to set.
     * @since 1.1
     */
    public void setLabel(String label){
        this.label = label;
    }
}
