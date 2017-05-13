package com.irh.transaction.controllers.views;


import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.utils.exceptions.HCHCException;
import com.irh.transaction.model.finance.FinancialReport;
import com.irh.transaction.services.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ift.CellProcessor;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The view controller for views to manage {@link FinancialReport}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/finance/report")
public class FinancialReportController extends BaseController{

    /**
     * The {@link FinancialReportService} instance.
     */
    @Autowired
    private FinancialReportService financialReportService;

    /**
     * Gets the path of the "finance/report" view.
     *
     * @return the path of the "finance/report" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "finance/report";
    }

    /**
     * Exports the financial reports to CSV file and writes it to the response.
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
    public void export(@RequestParam Map<String, String> params, HttpServletResponse response)
            throws HCHCException{
        try {
            ReportSearchFilter filter = new ReportSearchFilter();
            WebHelper.setSearchFilter(filter, params, getAccount());
            if (params.containsKey("groupByBranch")) {
                filter.setGroupByBranch(Boolean.valueOf(params.get("groupByBranch")));
            }
            List<FinancialReport> items = financialReportService.search(filter).getItems();
            String[] headers;
            String[] props;
            CellProcessor[] cellProcessors;
            if (!filter.isGroupByBranch()) {
                headers = new String[]{
                        "日期",
                        "订单数",
                        "现金支付",
                        "刷卡支付",
                        "微信支付",
                        "支付宝支付",
                        "会员卡支付",
                        "现金券",
                        "签单",
                        "赠送",
                        "折扣",
                        "营业实收",
                        "现金充值",
                        "刷卡充值",
                        "会员卡充值总额",
                        "微信退款",
                        "支付宝退款",
                        "现金退款",
                        "刷卡退款",
                        "退款总额"
                };
                props = new String[]{
                        "date",
                        "orders",
                        "cashSales",
                        "unipaySales",
                        "wechatSales",
                        "alipaySales",
                        "vipCardSales",
                        "coupons",
                        "signedBills",
                        "gifts",
                        "discounts",
                        "totalSales",
                        "cashRecharge",
                        "unipayRecharge",
                        "totalRecharge",
                        "wechatRefund",
                        "alipayRefund",
                        "cashRefund",
                        "unipayRefund",
                        "totalRefund"
                };
                cellProcessors = new CellProcessor[]{
                        new FmtDate("yyyy-MM-dd"), null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null
                };
            } else {
                headers = new String[]{
                        "店名",
                        "起始时间",
                        "截止时间",
                        "订单数",
                        "现金支付",
                        "刷卡支付",
                        "微信支付",
                        "支付宝支付",
                        "会员卡支付",
                        "现金券",
                        "签单",
                        "赠送",
                        "折扣",
                        "营业实收",
                        "现金充值",
                        "刷卡充值",
                        "会员卡充值总额",
                        "微信退款",
                        "支付宝退款",
                        "现金退款",
                        "刷卡退款",
                        "退款总额"
                };
                props = new String[]{
                        "branch",
                        "startDate",
                        "endDate",
                        "orders",
                        "cashSales",
                        "unipaySales",
                        "wechatSales",
                        "alipaySales",
                        "vipCardSales",
                        "coupons",
                        "signedBills",
                        "gifts",
                        "discounts",
                        "totalSales",
                        "cashRecharge",
                        "unipayRecharge",
                        "totalRecharge",
                        "wechatRefund",
                        "alipayRefund",
                        "cashRefund",
                        "unipayRefund",
                        "totalRefund"
                };
                cellProcessors = new CellProcessor[]{
                        null, new FmtDate("yyyy-MM-dd"), new FmtDate("yyyy-MM-dd"), null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                        null
                };
            }

            ArrayList<FinancialReportRow> rows = new ArrayList<>();
            for (FinancialReport item : items) {
                FinancialReportRow row = new FinancialReportRow();
                if (item.getBranch() != null) {
                    row.setBranch(item.getBranch().getName());
                }
                row.setDate(item.getDate());
                row.setDiscounts(item.getDiscounts());
                row.setOrders(item.getOrders());
                row.setTotalRefund(item.getTotalRefund());
                row.setWechatRefund(item.getWechatRefund());
                row.setAlipayRefund(item.getAlipayRefund());
                row.setCashRefund(item.getCashRefund());
                row.setUnipayRefund(item.getUnipayRefund());
                row.setTotalRecharge(item.getTotalRecharge());
                row.setCashRecharge(item.getCashRecharge());
                row.setUnipayRecharge(item.getUnipayRecharge());
                row.setStartDate(filter.getDateFrom());
                row.setEndDate(filter.getDateTo());
                row.setCashSales(item.getCashSales());
                row.setUnipaySales(item.getUnipaySales());
                row.setWechatSales(item.getWechatSales());
                row.setAlipaySales(item.getAlipaySales());
                row.setCoupons(item.getCoupons());
                row.setVipCardSales(item.getVipCardSales());
                row.setGifts(item.getGifts());
                row.setSignedBills(item.getSignedBills());
                row.setTotalSales(item.getTotalSales());
                rows.add(row);
            }

            WebHelper.exportCsv(headers, rows, props, "财务报表-" + (filter.isGroupByBranch() ? "店铺" : "汇总") + ".csv",
                    cellProcessors, response);
        } catch (Exception ex) {
            throw WebHelper.logException(getLogger(), FinancialReportService.class.getName()
                            + "#export(Map<String, String>, HttpServletResponse)",
                    new HCHCException("Error occurred while processing the request.", ex));
        }
    }

    /**
     * The simple POJO class for a row representing an {@link FinancialReport} in an csv to export.
     *
     * @author Iritchie.ren
     * @version 1.0
     */
    public class FinancialReportRow {

        /**
         * The date.
         */
        private Date date;

        /**
         * The start date.
         */
        private Date startDate;

        /**
         * The end date.
         */
        private Date endDate;

        /**
         * The branch name.
         */
        private String branch;

        /**
         * The total amount of sales.
         */
        private BigDecimal totalSales;

        /**
         * The amount of cash sales.
         */
        private BigDecimal cashSales;

        /**
         * The amount of UniPay sales.
         */
        private BigDecimal unipaySales;

        /**
         * The amount of AliPay sales.
         */
        private BigDecimal alipaySales;

        /**
         * The amount of WeChat sales.
         */
        private BigDecimal wechatSales;

        /**
         * The amount of VIP card sales.
         */
        private BigDecimal vipCardSales;

        /**
         * The amount of coupons used.
         */
        private BigDecimal coupons;

        /**
         * The amount of discounts.
         */
        private BigDecimal discounts;

        /**
         * The amount of signed bills.
         */
        private BigDecimal signedBills;

        /**
         * The total amount of refund.
         */
        private BigDecimal totalRefund;

        /**
         * The amount of refund via cash.
         */
        private BigDecimal cashRefund;

        /**
         * The amount of refund via UniPay.
         */
        private BigDecimal unipayRefund;

        /**
         * The amount of refund via AliPay.
         */
        private BigDecimal alipayRefund;

        /**
         * The amount of refund via WeChat.
         */
        private BigDecimal wechatRefund;

        /**
         * The amount of VIP card recharge.
         */
        private BigDecimal totalRecharge;

        /**
         * The amount of VIP card recharge via cash.
         */
        private BigDecimal cashRecharge;

        /**
         * The amount of VIP card recharge via UniPay.
         */
        private BigDecimal unipayRecharge;

        /**
         * The amount of gifts.
         */
        private BigDecimal gifts;

        /**
         * The amount of invoices.
         *
         * @since 1.1
         */
        private BigDecimal invoices;

        /**
         * The total number of orders.
         */
        private int orders;

        /**
         * Gets the date.
         *
         * @return the date.
         */
        public Date getDate() {
            return date;
        }

        /**
         * Sets the date.
         *
         * @param date
         *         the date to set.
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * Gets the startDate.
         *
         * @return the startDate.
         */
        public Date getStartDate() {
            return startDate;
        }

        /**
         * Sets the startDate.
         *
         * @param startDate
         *         the startDate to set.
         */
        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        /**
         * Gets the endDate.
         *
         * @return the endDate.
         */
        public Date getEndDate() {
            return endDate;
        }

        /**
         * Sets the endDate.
         *
         * @param endDate
         *         the endDate to set.
         */
        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        /**
         * Gets the branch.
         *
         * @return the branch.
         */
        public String getBranch() {
            return branch;
        }

        /**
         * Sets the branch.
         *
         * @param branch
         *         the branch to set.
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

        /**
         * Gets the totalSales.
         *
         * @return the totalSales.
         */
        public BigDecimal getTotalSales() {
            return totalSales;
        }

        /**
         * Sets the totalSales.
         *
         * @param totalSales
         *         the totalSales to set.
         */
        public void setTotalSales(BigDecimal totalSales) {
            this.totalSales = totalSales;
        }

        /**
         * Gets the cashSales.
         *
         * @return the cashSales.
         */
        public BigDecimal getCashSales() {
            return cashSales;
        }

        /**
         * Sets the cashSales.
         *
         * @param cashSales
         *         the cashSales to set.
         */
        public void setCashSales(BigDecimal cashSales) {
            this.cashSales = cashSales;
        }

        /**
         * Gets the unipaySales.
         *
         * @return the unipaySales.
         */
        public BigDecimal getUnipaySales() {
            return unipaySales;
        }

        /**
         * Sets the unipaySales.
         *
         * @param unipaySales
         *         the unipaySales to set.
         */
        public void setUnipaySales(BigDecimal unipaySales) {
            this.unipaySales = unipaySales;
        }

        /**
         * Gets the alipaySales.
         *
         * @return the alipaySales.
         */
        public BigDecimal getAlipaySales() {
            return alipaySales;
        }

        /**
         * Sets the alipaySales.
         *
         * @param alipaySales
         *         the alipaySales to set.
         */
        public void setAlipaySales(BigDecimal alipaySales) {
            this.alipaySales = alipaySales;
        }

        /**
         * Gets the wechatSales.
         *
         * @return the wechatSales.
         */
        public BigDecimal getWechatSales() {
            return wechatSales;
        }

        /**
         * Sets the wechatSales.
         *
         * @param wechatSales
         *         the wechatSales to set.
         */
        public void setWechatSales(BigDecimal wechatSales) {
            this.wechatSales = wechatSales;
        }

        /**
         * Gets the vipCardSales.
         *
         * @return the vipCardSales.
         */
        public BigDecimal getVipCardSales() {
            return vipCardSales;
        }

        /**
         * Sets the vipCardSales.
         *
         * @param vipCardSales
         *         the vipCardSales to set.
         */
        public void setVipCardSales(BigDecimal vipCardSales) {
            this.vipCardSales = vipCardSales;
        }

        /**
         * Gets the coupons.
         *
         * @return the coupons.
         */
        public BigDecimal getCoupons() {
            return coupons;
        }

        /**
         * Sets the coupons.
         *
         * @param coupons
         *         the coupons to set.
         */
        public void setCoupons(BigDecimal coupons) {
            this.coupons = coupons;
        }

        /**
         * Gets the discounts.
         *
         * @return the discounts.
         */
        public BigDecimal getDiscounts() {
            return discounts;
        }

        /**
         * Sets the discounts.
         *
         * @param discounts
         *         the discounts to set.
         */
        public void setDiscounts(BigDecimal discounts) {
            this.discounts = discounts;
        }

        /**
         * Gets the signedBills.
         *
         * @return the signedBills.
         */
        public BigDecimal getSignedBills() {
            return signedBills;
        }

        /**
         * Sets the signedBills.
         *
         * @param signedBills
         *         the signedBills to set.
         */
        public void setSignedBills(BigDecimal signedBills) {
            this.signedBills = signedBills;
        }

        /**
         * Gets the totalRefund.
         *
         * @return the totalRefund.
         */
        public BigDecimal getTotalRefund() {
            return totalRefund;
        }

        /**
         * Sets the totalRefund.
         *
         * @param totalRefund
         *         the totalRefund to set.
         */
        public void setTotalRefund(BigDecimal totalRefund) {
            this.totalRefund = totalRefund;
        }

        /**
         * Gets the cashRefund.
         *
         * @return the cashRefund.
         */
        public BigDecimal getCashRefund() {
            return cashRefund;
        }

        /**
         * Sets the cashRefund.
         *
         * @param cashRefund
         *         the cashRefund to set.
         */
        public void setCashRefund(BigDecimal cashRefund) {
            this.cashRefund = cashRefund;
        }

        /**
         * Gets the unipayRefund.
         *
         * @return the unipayRefund.
         */
        public BigDecimal getUnipayRefund() {
            return unipayRefund;
        }

        /**
         * Sets the unipayRefund.
         *
         * @param unipayRefund
         *         the unipayRefund to set.
         */
        public void setUnipayRefund(BigDecimal unipayRefund) {
            this.unipayRefund = unipayRefund;
        }

        /**
         * Gets the alipayRefund.
         *
         * @return the alipayRefund.
         */
        public BigDecimal getAlipayRefund() {
            return alipayRefund;
        }

        /**
         * Sets the alipayRefund.
         *
         * @param alipayRefund
         *         the alipayRefund to set.
         */
        public void setAlipayRefund(BigDecimal alipayRefund) {
            this.alipayRefund = alipayRefund;
        }

        /**
         * Gets the wechatRefund.
         *
         * @return the wechatRefund.
         */
        public BigDecimal getWechatRefund() {
            return wechatRefund;
        }

        /**
         * Sets the wechatRefund.
         *
         * @param wechatRefund
         *         the wechatRefund to set.
         */
        public void setWechatRefund(BigDecimal wechatRefund) {
            this.wechatRefund = wechatRefund;
        }

        /**
         * Gets the totalRecharge.
         *
         * @return the totalRecharge.
         */
        public BigDecimal getTotalRecharge() {
            return totalRecharge;
        }

        /**
         * Sets the totalRecharge.
         *
         * @param totalRecharge
         *         the totalRecharge to set.
         */
        public void setTotalRecharge(BigDecimal totalRecharge) {
            this.totalRecharge = totalRecharge;
        }

        /**
         * Gets the cashRecharge.
         *
         * @return the cashRecharge.
         */
        public BigDecimal getCashRecharge() {
            return cashRecharge;
        }

        /**
         * Sets the cashRecharge.
         *
         * @param cashRecharge
         *         the cashRecharge to set.
         */
        public void setCashRecharge(BigDecimal cashRecharge) {
            this.cashRecharge = cashRecharge;
        }

        /**
         * Gets the unipayRecharge.
         *
         * @return the unipayRecharge.
         */
        public BigDecimal getUnipayRecharge() {
            return unipayRecharge;
        }

        /**
         * Sets the unipayRecharge.
         *
         * @param unipayRecharge
         *         the unipayRecharge to set.
         */
        public void setUnipayRecharge(BigDecimal unipayRecharge) {
            this.unipayRecharge = unipayRecharge;
        }

        /**
         * Gets the gifts.
         *
         * @return the gifts.
         */
        public BigDecimal getGifts() {
            return gifts;
        }

        /**
         * Sets the gifts.
         *
         * @param gifts
         *         the gifts to set.
         */
        public void setGifts(BigDecimal gifts) {
            this.gifts = gifts;
        }

        /**
         * Gets the invoices.
         *
         * @return the invoices.
         */
        public BigDecimal getInvoices() {
            return invoices;
        }

        /**
         * Sets the invoices.
         *
         * @param invoices
         *         the invoices to set.
         */
        public void setInvoices(BigDecimal invoices) {
            this.invoices = invoices;
        }

        /**
         * Gets the orders.
         *
         * @return the orders.
         */
        public int getOrders() {
            return orders;
        }

        /**
         * Sets the orders.
         *
         * @param orders
         *         the orders to set.
         */
        public void setOrders(int orders) {
            this.orders = orders;
        }
    }
}
