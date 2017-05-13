package com.irh.transaction;

import com.irh.transaction.dto.search.*;
import com.irh.transaction.model.account.Account;
import com.irh.transaction.model.account.RoleLevel;
import com.irh.transaction.model.common.PayType;
import com.irh.transaction.model.common.Platform;
import com.irh.transaction.model.marketing.VipCardStatus;
import com.irh.transaction.model.order.OrderServiceType;
import com.irh.transaction.model.order.OrderStatus;
import org.slf4j.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Contains helper members for this component.
 *
 * <p> <b>Thread Safety:</b> This class is immutable and is thread safe. </p>
 *
 * @author Iritchie.ren
 * @version 1.0
 */
public class WebHelper{

    /**
     * Represents the error message.
     */
    private static final String ERROR_MESSAGE = "Error in method %1$s. Details: ";

    /**
     * Prevents this class from being initialized.
     */
    private WebHelper(){
    }

    /**
     * Logs the given exception and message at <code>ERROR</code> level.
     *
     * @param logger    the logger. If <code>null</code>, nothing will be logged.
     * @param signature the method signature.
     * @param ex        the thrown exception.
     * @param <T>       the type of the exception.
     * @return the logged exception.
     */
    public static <T extends Throwable> T logException(Logger logger, String signature, T ex){
        if(logger == null){
            return ex;
        }
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        logger.error(String.format(ERROR_MESSAGE, signature) + sw.toString());
        return ex;
    }

    public static void setSearchFilter(NamedEntitySearchFilter filter,
                                       Map<String, String> params, Account account){
        if(params.containsKey("name")){
            filter.setName(params.get("name"));
        }
        setSearchFilterParams(filter, params, account);
    }

    public static void setSearchFilter(DateFilter filter, Map<String, String> params,
                                       Account account) throws Exception{
        if(params == null){
            return;
        }
        DateFormat dateFormat = null;
        setSearchFilterParams(filter, params, account);
        if(params.containsKey("dateFrom")){
            dateFormat = getDateFormat(null, false);
            filter.setDateFrom(dateFormat.parse(params.get("dateFrom")));
        }
        if(params.containsKey("dateTo")){
            dateFormat = getDateFormat(dateFormat, false);
            filter.setDateTo(dateFormat.parse(params.get("dateTo")));
        }
    }

    public static void setSearchFilter(OrderSearchFilter filter, Map<String, String> params,
                                       Account account) throws Exception{
        if(params == null){
            return;
        }
        DateFormat dateFormat = null;
        setSearchFilterParams(filter, params, account);
        if(params.containsKey("createdAtFrom")){
            dateFormat = getDateFormat(null, true);
            filter.setCreatedAtFrom((dateFormat.parse(params.get("createdAtFrom"))));
        }
        if(params.containsKey("createdAtTo")){
            dateFormat = getDateFormat(dateFormat, true);
            filter.setCreatedAtTo(dateFormat.parse(params.get("createdAtTo")));
        }
        if(params.containsKey("priceFrom")){
            filter.setPriceFrom(new BigDecimal(params.get("priceFrom")));
        }
        if(params.containsKey("priceTo")){
            filter.setPriceTo(new BigDecimal(params.get("priceTo")));
        }
        if(params.containsKey("platform")){
            filter.setPlatform(Platform.valueOf(params.get("platform")));
        }
        if(params.containsKey("payType")){
            filter.setPayType(PayType.valueOf(params.get("payType")));
        }
        if(params.containsKey("serviceType")){
            filter.setServiceType(OrderServiceType.valueOf(params.get("serviceType")));
        }
        if(params.containsKey("status")){
            filter.setStatus(OrderStatus.valueOf(params.get("status")));
        }
    }

    public static void setSearchFilterParams(SearchFilter filter, Map<String, String> params,
                                             Account account){
        filter.setHqId(account.getHqId());
        RoleLevel roleLevel = account.getRole().getLevel();
        if(roleLevel == RoleLevel.BRANCH){
            filter.setBranchId(account.getBranch().getId());
            filter.setGroupId(account.getBranchGroup().getId());
        }else{
            if(params == null){
                return;
            }
            if(params.containsKey("branchId")){
                filter.setBranchId(Long.valueOf(params.get("branchId")));
            }
            if(roleLevel == RoleLevel.BRANCH_GROUP){
                filter.setGroupId(account.getBranchGroup().getId());
            }else if(params.containsKey("groupId")){
                filter.setGroupId(Long.valueOf(params.get("groupId")));
            }
        }
        if(params == null){
            return;
        }
        if(params.containsKey("page")){
            filter.setPage(Integer.valueOf(params.get("page")));
            if(params.containsKey("size")){
                filter.setSize(Integer.valueOf(params.get("size")));
            }
        }
        if(params.containsKey("sortBy")){
            filter.setSortBy(params.get("sortBy"));
            if(params.containsKey("sortOrder")){
                filter.setSortOrder(SortOrder.valueOf(params.get("sortOrder")));
            }
        }
    }

    private static DateFormat getDateFormat(DateFormat dateFormat, boolean datetime){
        return dateFormat != null ? dateFormat : new SimpleDateFormat(datetime ?
                "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd");
    }

    public static void setVipSearchFilter(VipCardSearchFilter filter, Map<String, String> params, Account account){
        filter.setHqId(account.getHqId());
        if(params.containsKey("status")){
            filter.setStatus(VipCardStatus.valueOf(params.get("status")));
        }
        setSearchFilterParams(filter, params, account);
    }

    /**
     * Exports the items to the response download.
     *
     * @param headers        the header columns.
     * @param items          the list of items to export.
     * @param props          the properties to map.
     * @param fileName       the csv file name.
     * @param cellProcessors an array of CellProcessors used to further process data before it is written
     * @param response       the HTTP response.
     * @param <T>            the concrete type of the item.
     * @throws IOException if any error occurs.
     * @see CsvBeanWriter
     */
    public static <T> void exportCsv(String[] headers, List<T> items, String[] props, String fileName,
                                     CellProcessor[] cellProcessors, HttpServletResponse response)
            throws IOException{
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        response.setContentType("text/csv;charset=gb18030");
        CsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(headers);
        for(T item : items){
            if(cellProcessors != null){
                csvWriter.write(item, props, cellProcessors);
            }else{
                csvWriter.write(item, props);
            }
        }
        csvWriter.close();
    }

    /**
     * Gets the string representation of the given {@link PayType}.
     *
     * @param payType the pay type.
     * @return the string representation of the given {@link PayType}.
     */
    public static String getPayType(PayType payType){
        if(payType == PayType.UNI_PAY){
            return "刷卡";
        }
        if(payType == PayType.CASH){
            return "现金";
        }
        if(payType == PayType.WECHAT){
            return "微信支付";
        }
        if(payType == PayType.ALIPAY){
            return "支付宝";
        }
        if(payType == PayType.VIPCARD){
            return "会员卡支付";
        }
        if(payType == PayType.SIGNED){
            return "签单";
        }
        return "现金券";
    }
}
