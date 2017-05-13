package com.irh.transaction.controllers.views;

import com.irh.transaction.WebHelper;
import com.irh.transaction.controllers.BaseController;

import com.irh.transaction.utils.exceptions.HCHCException;
import com.irh.transaction.model.finance.VipCardReport;
import com.irh.transaction.dto.search.ReportSearchFilter;
import com.irh.transaction.services.VipCardReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The view controller for views to manage {@link VipCardReport}.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/finance/vip-card")
public class VipCardReportController extends BaseController {

    /**
     * The {@link VipCardReportService} instance.
     */
    @Autowired
    private VipCardReportService vipCardReportService;

    /**
     * Gets the path of the "finance/vip-card" view.
     *
     * @return the path of the "finance/vip-card" view.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String view() {
        return "finance/vip-card";
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
            List<VipCardReport> items = vipCardReportService.search(filter).getItems();
            List<VipCardReportRow> rows = new ArrayList<>();
            for (VipCardReport report : items) {
                VipCardReportRow row = new VipCardReportRow();
                if (report.getBranch() != null) {
                    row.setBranchName(report.getBranch().getName());
                }
                if (report.getVipCard() != null) {
                    row.setVipCardBalance(report.getVipCard().getBalance());
                    row.setVipCardInvoiceQuota(report.getVipCard().getInvoiceQuota());
                    row.setVipCardName(report.getVipCard().getName());
                    row.setVipCardNumber(report.getVipCard().getNumber());
                }
                row.setInvoice(report.getInvoice());
                row.setTotalRecharge(report.getTotalRecharge());
                row.setCashRecharge(report.getCashRecharge());
                row.setUnipayRecharge(report.getUnipayRecharge());
                row.setConsume(report.getConsume());
                rows.add(row);
            }

            String[] headers;
            String[] props;
            if (!filter.isGroupByBranch()) {
                headers = new String[]{
                        "卡号",
                        "姓名",
                        "余额",
                        "发票余额",
                        "现金充值",
                        "刷卡充值",
                        "充值总额",
                        "消费总额",
                        "发票总额"
                };
                props = new String[]{
                        "vipCardNumber",
                        "vipCardName",
                        "vipCardBalance",
                        "vipCardInvoiceQuota",
                        "cashRecharge",
                        "unipayRecharge",
                        "totalRecharge",
                        "consume",
                        "invoice"
                };
            } else {
                headers = new String[]{
                        "分店",
                        "现金充值",
                        "刷卡充值",
                        "充值总额",
                        "消费总额",
                        "发票总额"
                };
                props = new String[]{
                        "branchName",
                        "cashRecharge",
                        "unipayRecharge",
                        "totalRecharge",
                        "consume",
                        "invoice"
                };
            }
            WebHelper.exportCsv(headers, rows, props, "会员卡报表-" + (filter.isGroupByBranch() ? "分店" : "详情"), null,
                    response);
        } catch (Exception ex) {
            throw WebHelper.logException(getLogger(), VipCardReportController.class.getName()
                            + "#export(Map<String, String>, HttpServletResponse)",
                    new HCHCException("Error occurred while processing the request.", ex));
        }
    }

    public class VipCardReportRow {

        /**
         * The branch name.
         */
        private String branchName;

        /**
         * The VIP card name.
         */
        private String vipCardName;

        /**
         * The VIP card number.
         */
        private String vipCardNumber;

        /**
         * The VIP card balance.
         */
        private BigDecimal vipCardBalance;

        /**
         * The VIP card invoice quota.
         */
        private BigDecimal vipCardInvoiceQuota;

        /**
         * The total recharge amount.
         */
        private BigDecimal totalRecharge;

        /**
         * The recharge amount via cash.
         */
        private BigDecimal cashRecharge;

        /**
         * The recharge amount via UniPay.
         */
        private BigDecimal unipayRecharge;

        /**
         * The total consume amount.
         */
        private BigDecimal consume;

        /**
         * The total invoice amount.
         */
        private BigDecimal invoice;

        /**
         * Gets the branchName.
         *
         * @return the branchName.
         */
        public String getBranchName() {
            return branchName;
        }

        /**
         * Sets the branchName.
         *
         * @param branchName
         *         the branchName to set.
         */
        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        /**
         * Gets the vipCardName.
         *
         * @return the vipCardName.
         */
        public String getVipCardName() {
            return vipCardName;
        }

        /**
         * Sets the vipCardName.
         *
         * @param vipCardName
         *         the vipCardName to set.
         */
        public void setVipCardName(String vipCardName) {
            this.vipCardName = vipCardName;
        }

        /**
         * Gets the vipCardNumber.
         *
         * @return the vipCardNumber.
         */
        public String getVipCardNumber() {
            return vipCardNumber;
        }

        /**
         * Sets the vipCardNumber.
         *
         * @param vipCardNumber
         *         the vipCardNumber to set.
         */
        public void setVipCardNumber(String vipCardNumber) {
            this.vipCardNumber = vipCardNumber;
        }

        /**
         * Gets the vipCardBalance.
         *
         * @return the vipCardBalance.
         */
        public BigDecimal getVipCardBalance() {
            return vipCardBalance;
        }

        /**
         * Sets the vipCardBalance.
         *
         * @param vipCardBalance
         *         the vipCardBalance to set.
         */
        public void setVipCardBalance(BigDecimal vipCardBalance) {
            this.vipCardBalance = vipCardBalance;
        }

        /**
         * Gets the vipCardInvoiceQuota.
         *
         * @return the vipCardInvoiceQuota.
         */
        public BigDecimal getVipCardInvoiceQuota() {
            return vipCardInvoiceQuota;
        }

        /**
         * Sets the vipCardInvoiceQuota.
         *
         * @param vipCardInvoiceQuota
         *         the vipCardInvoiceQuota to set.
         */
        public void setVipCardInvoiceQuota(BigDecimal vipCardInvoiceQuota) {
            this.vipCardInvoiceQuota = vipCardInvoiceQuota;
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
         * Gets the consume.
         *
         * @return the consume.
         */
        public BigDecimal getConsume() {
            return consume;
        }

        /**
         * Sets the consume.
         *
         * @param consume
         *         the consume to set.
         */
        public void setConsume(BigDecimal consume) {
            this.consume = consume;
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
