package com.irh.transaction.controllers.views;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;

import com.irh.transaction.utils.exceptions.HCHCException;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.order.Order;
import com.irh.transaction.model.order.OrderInvoiceRecord;
import com.irh.transaction.model.order.OrderPaymentRecord;
import com.irh.transaction.dto.search.OrderSearchFilter;
import com.irh.transaction.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ift.CellProcessor;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The view controller for views for order transactions.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/finance/order")
public class OrderTransactionController extends BaseController {

    /**
     * The {@link OrderService} instance.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Gets the path of the "finance/order" view.
     *
     * @return the path of the "finance/order" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "finance/order";
    }

    /**
     * Exports the order transactions to CSV file and writes it to the response.
     *
     * @param params
     *         the request parameters.
     * @param response
     *         the HTTP response.
     *
     * @throws HCHCException
     *         if any error occurs.
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(Map<String, String> params, HttpServletResponse response) throws HCHCException{
        try {
            OrderSearchFilter filter = new OrderSearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            List<Order> items = orderService.search(filter).getItems();
            String[] headers = {
                    "时间",
                    "订单号",
                    "支付方式",
                    "金额",
                    "实收",
                    "优惠券",
                    "打折",
                    "发票",
                    "原订单号"
            };
            String[] props = {
                    "createdAt",
                    "bill",
                    "payType",
                    "price",
                    "received",
                    "coupon",
                    "discount",
                    "invoice",
                    "originalBill"
            };
            ArrayList<OrderTransactionRow> rows = new ArrayList<OrderTransactionRow>();
            for (Order order : items) {
                OrderTransactionRow row = new OrderTransactionRow();
                row.setCreatedAt(order.getCreatedAt());
                row.setBill(order.getBill());
                row.setPayType(WebHelper.getPayType(order.getPayType()));
                row.setPrice(order.getPrice());
                row.setReceived(order.getPrice());
                row.setOriginalBill(order.getOriginalBill());
                row.setDiscount(order.getDiscount() * 100);
                if (order.getPaymentRecords() != null) {
                    for (OrderPaymentRecord record : order.getPaymentRecords()) {
                        if (record.getPayType() == PayType.COUPON) {
                            row.setCoupon(record.getAmount());
                            row.setReceived(row.getReceived().subtract(record.getAmount()));
                            break;
                        }
                    }
                }
                row.setInvoice(BigDecimal.ZERO);
                if (order.getInvoiceRecords() != null) {
                    for (OrderInvoiceRecord record : order.getInvoiceRecords()) {
                        row.setInvoice(row.getInvoice().add(record.getAmount()));
                    }
                }
                rows.add(row);
            }
            WebHelper.exportCsv(headers, rows, props, "订单流水.csv",
                    new CellProcessor[]{new FmtDate("yyyy-MM-dd HH:mm"), null, null, null,
                            null, null, null, null}, response);
        } catch (Exception ex) {
            throw WebHelper.logException(getLogger(), OrderTransactionController.class.getName()
                            + "#export(Map<String, String>, HttpServletResponse)",
                    new HCHCException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * The simple POJO class for a row representing an order transaction in an csv to export.
     *
     * @author Iritchie.ren
     * @version 1.0
     */
    public class OrderTransactionRow {

        /**
         * The date time when the order was created.
         */
        private Date createdAt;

        /**
         * The order bill number.
         */
        private String bill;

        /**
         * The pay type.
         */
        private String payType;

        /**
         * The original amount.
         */
        private BigDecimal price;

        /**
         * The discount.
         */
        private Float discount;

        /**
         * The amount of coupon.
         */
        private BigDecimal coupon;

        /**
         * The actual received.
         */
        private BigDecimal received;

        /**
         * The bill number of the original order.
         */
        private String originalBill;

        /**
         * The invoice amount.
         */
        private BigDecimal invoice;

        /**
         * Gets the createdAt.
         *
         * @return the createdAt.
         */
        public Date getCreatedAt() {
            return createdAt;
        }

        /**
         * Sets the createdAt.
         *
         * @param createdAt
         *         the createdAt to set.
         */
        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        /**
         * Gets the bill.
         *
         * @return the bill.
         */
        public String getBill() {
            return bill;
        }

        /**
         * Sets the bill.
         *
         * @param bill
         *         the bill to set.
         */
        public void setBill(String bill) {
            this.bill = bill;
        }

        /**
         * Gets the payType.
         *
         * @return the payType.
         */
        public String getPayType() {
            return payType;
        }

        /**
         * Sets the payType.
         *
         * @param payType
         *         the payType to set.
         */
        public void setPayType(String payType) {
            this.payType = payType;
        }

        /**
         * Gets the price.
         *
         * @return the price.
         */
        public BigDecimal getPrice() {
            return price;
        }

        /**
         * Sets the price.
         *
         * @param price
         *         the price to set.
         */
        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        /**
         * Gets the coupon.
         *
         * @return the coupon.
         */
        public BigDecimal getCoupon() {
            return coupon;
        }

        /**
         * Sets the coupon.
         *
         * @param coupon
         *         the coupon to set.
         */
        public void setCoupon(BigDecimal coupon) {
            this.coupon = coupon;
        }

        /**
         * Gets the discount.
         *
         * @return the discount.
         */
        public Float getDiscount() {
            return discount;
        }

        /**
         * Sets the discount.
         *
         * @param discount
         *         the discount to set.
         */
        public void setDiscount(Float discount) {
            this.discount = discount;
        }

        /**
         * Gets the originalBill.
         *
         * @return the originalBill.
         */
        public String getOriginalBill() {
            return originalBill;
        }

        /**
         * Sets the originalBill.
         *
         * @param originalBill
         *         the originalBill to set.
         */
        public void setOriginalBill(String originalBill) {
            this.originalBill = originalBill;
        }

        /**
         * Gets the received.
         *
         * @return the received.
         */
        public BigDecimal getReceived() {
            return received;
        }

        /**
         * Sets the received.
         *
         * @param received
         *         the received to set.
         */
        public void setReceived(BigDecimal received) {
            this.received = received;
        }

        /**
         * Gets the invoice.
         *
         * @return the invoice.
         */
        public BigDecimal getInvoice() {
            return invoice;
        }

        /**
         * Sets the invoice.
         *
         * @param invoice
         *         the invoice to set.
         */
        public void setInvoice(BigDecimal invoice) {
            this.invoice = invoice;
        }
    }
}
