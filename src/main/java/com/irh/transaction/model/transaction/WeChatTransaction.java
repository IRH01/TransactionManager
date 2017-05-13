package com.irh.transaction.model.transaction;


import com.irh.transaction.model.common.IdentifiableEntity;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public class WeChatTransaction extends IdentifiableEntity{
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    private String openId;
    private String isSubscribe;
    private String tradeType;
    private String bankType;
    private String totalFee;
    private String feeType;
    private String cashFee;
    private String cashFeeType;
    private String couponFee;
    private String transactionId;
    private String outTradeNo;
    private String timeEnd;

    public String getBankType(){
        return bankType;
    }

    public void setBankType(String bankType){
        this.bankType = bankType;
    }

    public String getCashFee(){
        return cashFee;
    }

    public void setCashFee(String cashFee){
        this.cashFee = cashFee;
    }

    public String getCashFeeType(){
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType){
        this.cashFeeType = cashFeeType;
    }

    public String getCouponFee(){
        return couponFee;
    }

    public void setCouponFee(String couponFee){
        this.couponFee = couponFee;
    }

    public String getErrCode(){
        return errCode;
    }

    public void setErrCode(String errCode){
        this.errCode = errCode;
    }

    public String getErrCodeDes(){
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes){
        this.errCodeDes = errCodeDes;
    }

    public String getFeeType(){
        return feeType;
    }

    public void setFeeType(String feeType){
        this.feeType = feeType;
    }

    public String getIsSubscribe(){
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe){
        this.isSubscribe = isSubscribe;
    }

    public String getOpenId(){
        return openId;
    }

    public void setOpenId(String openId){
        this.openId = openId;
    }

    public String getOutTradeNo(){
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }

    public String getResultCode(){
        return resultCode;
    }

    public void setResultCode(String resultCode){
        this.resultCode = resultCode;
    }

    public String getReturnCode(){
        return returnCode;
    }

    public void setReturnCode(String returnCode){
        this.returnCode = returnCode;
    }

    public String getReturnMsg(){
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg){
        this.returnMsg = returnMsg;
    }

    public String getTimeEnd(){
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd){
        this.timeEnd = timeEnd;
    }

    public String getTotalFee(){
        return totalFee;
    }

    public void setTotalFee(String totalFee){
        this.totalFee = totalFee;
    }

    public String getTradeType(){
        return tradeType;
    }

    public void setTradeType(String tradeType){
        this.tradeType = tradeType;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }
}
