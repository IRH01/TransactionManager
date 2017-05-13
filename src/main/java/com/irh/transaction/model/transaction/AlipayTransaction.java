package com.irh.transaction.model.transaction;

import com.irh.transaction.model.common.IdentifiableEntity;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public class AlipayTransaction extends IdentifiableEntity{
    private String tradeNo;
    private String outTradeNo;
    private String outBizNo;
    private String notifyTime;
    private String notifyType;
    private String notifyId;
    private String buyerId;
    private String buyerLogonId;
    private String tradeStatus;
    private String totalAmount;
    private String receiptAmount;
    private String invoiceAmount;
    private String buyerPayAmount;
    private String pointAmount;
    private String refundFee;
    private String sendBackFee;
    private String subject;
    private String body;
    private String gmtCreate;
    private String gmtPayment;
    private String gmtRefund;
    private String gmtClose;
    private String fundBillList;

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBuyerId(){
        return buyerId;
    }

    public void setBuyerId(String buyerId){
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId(){
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId){
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerPayAmount(){
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount){
        this.buyerPayAmount = buyerPayAmount;
    }

    public String getFundBillList(){
        return fundBillList;
    }

    public void setFundBillList(String fundBillList){
        this.fundBillList = fundBillList;
    }

    public String getGmtClose(){
        return gmtClose;
    }

    public void setGmtClose(String gmtClose){
        this.gmtClose = gmtClose;
    }

    public String getGmtCreate(){
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPayment(){
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment){
        this.gmtPayment = gmtPayment;
    }

    public String getGmtRefund(){
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund){
        this.gmtRefund = gmtRefund;
    }

    public String getInvoiceAmount(){
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount){
        this.invoiceAmount = invoiceAmount;
    }

    public String getNotifyId(){
        return notifyId;
    }

    public void setNotifyId(String notifyId){
        this.notifyId = notifyId;
    }

    public String getNotifyTime(){
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime){
        this.notifyTime = notifyTime;
    }

    public String getNotifyType(){
        return notifyType;
    }

    public void setNotifyType(String notifyType){
        this.notifyType = notifyType;
    }

    public String getOutBizNo(){
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo){
        this.outBizNo = outBizNo;
    }

    public String getOutTradeNo(){
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }

    public String getPointAmount(){
        return pointAmount;
    }

    public void setPointAmount(String pointAmount){
        this.pointAmount = pointAmount;
    }

    public String getReceiptAmount(){
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount){
        this.receiptAmount = receiptAmount;
    }

    public String getRefundFee(){
        return refundFee;
    }

    public void setRefundFee(String refundFee){
        this.refundFee = refundFee;
    }

    public String getSendBackFee(){
        return sendBackFee;
    }

    public void setSendBackFee(String sendBackFee){
        this.sendBackFee = sendBackFee;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount){
        this.totalAmount = totalAmount;
    }

    public String getTradeNo(){
        return tradeNo;
    }

    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus(){
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus){
        this.tradeStatus = tradeStatus;
    }
}
