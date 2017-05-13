package com.irh.transaction.dao;


import com.irh.transaction.model.transaction.WeChatTransaction;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public interface WeChatTransactionMapper{

    WeChatTransaction findByOutTradeNo(String outTradeNo);

    void save(WeChatTransaction weChatTransaction);
}
