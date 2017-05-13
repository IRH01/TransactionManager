package com.irh.transaction.model.order;

import com.irh.transaction.model.common.IdentifiableEntity;
import com.irh.transaction.model.marketing.ProductDiscount;
import com.irh.transaction.model.product.Product;
import com.irh.transaction.model.product.ProductOption;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents an item in an order.
 *
 * <p> <b>Thread Safety:</b> This class is mutable and is not thread safe. </p>
 *
 * <p> <b>v1.1 Change Notes:</b> </p> <ol> <li> The utensil field has been replaced with {@link com.irh.transaction.model.order.OrderItem#serviceType}.
 * </li> <li> Discount info has been moved to the {@link com.irh.transaction.model.order.OrderItem#discounts} field. </li> <li> The ingredients has been
 * replaces with {@link com.irh.transaction.model.order.OrderItem#options}. </li> </ol>
 *
 * @author Iritchie.ren
 * @version 1.1
 */
public class OrderItem extends IdentifiableEntity{
    /**
     * The order id.
     */
    private long orderId;

    /**
     * The product.
     */
    private Product product;

    /**
     * The list of selected options.
     *
     * @since 1.1
     */
    private List<ProductOption> options;

    /**
     * The count.
     */
    private int count;

    /**
     * The price.
     */
    private BigDecimal price;

    /**
     * The original price.
     */
    private BigDecimal originalPrice;

    /**
     * The service type.
     *
     * @since 1.1
     */
    private com.irh.transaction.model.order.OrderServiceType serviceType;

    /**
     * The list of discounts apply.
     *
     * @since 1.1
     */
    private List<ProductDiscount> discounts;

    /**
     * The original item id, for refunded item only.
     */
    private Long originalId;

    /**
     * Gets the orderId.
     *
     * @return the orderId.
     */
    public long getOrderId(){
        return orderId;
    }

    /**
     * Sets the orderId.
     *
     * @param orderId the orderId to set.
     */
    public void setOrderId(long orderId){
        this.orderId = orderId;
    }

    /**
     * Gets the product.
     *
     * @return the product.
     */
    public Product getProduct(){
        return product;
    }

    /**
     * Sets the product.
     *
     * @param product the product to set.
     */
    public void setProduct(Product product){
        this.product = product;
    }

    /**
     * Gets the options.
     *
     * @return the options.
     * @since 1.1
     */
    public List<ProductOption> getOptions(){
        return options;
    }

    /**
     * Sets the options.
     *
     * @param options the options to set.
     * @since 1.1
     */
    public void setOptions(List<ProductOption> options){
        this.options = options;
    }

    /**
     * Gets the count.
     *
     * @return the count.
     */
    public int getCount(){
        return count;
    }

    /**
     * Sets the count.
     *
     * @param count the count to set.
     */
    public void setCount(int count){
        this.count = count;
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
     * Gets the originalPrice.
     *
     * @return the originalPrice.
     */
    public BigDecimal getOriginalPrice(){
        return originalPrice;
    }

    /**
     * Sets the originalPrice.
     *
     * @param originalPrice the originalPrice to set.
     */
    public void setOriginalPrice(BigDecimal originalPrice){
        this.originalPrice = originalPrice;
    }

    /**
     * Gets the serviceType.
     *
     * @return the serviceType.
     * @since 1.1
     */
    public com.irh.transaction.model.order.OrderServiceType getServiceType(){
        return serviceType;
    }

    /**
     * Sets the serviceType.
     *
     * @param serviceType the serviceType to set.
     * @since 1.1
     */
    public void setServiceType(OrderServiceType serviceType){
        this.serviceType = serviceType;
    }

    /**
     * Gets the discounts.
     *
     * @return the discounts.
     * @since 1.1
     */
    public List<ProductDiscount> getDiscounts(){
        return discounts;
    }

    /**
     * Sets the discounts.
     *
     * @param discounts the discounts to set.
     * @since 1.1
     */
    public void setDiscounts(List<ProductDiscount> discounts){
        this.discounts = discounts;
    }

    /**
     * Gets the originalId.
     *
     * @return the originalId.
     */
    public Long getOriginalId(){
        return originalId;
    }

    /**
     * Sets the originalId.
     *
     * @param originalId the originalId to set.
     */
    public void setOriginalId(Long originalId){
        this.originalId = originalId;
    }
}
