package com.irh.transaction.services;


import com.irh.transaction.model.transaction.WeChatTransaction;

/**
 * Created by iritchie.ren on 16/3/11.
 */
public interface WeChatTransactionService{

    WeChatTransaction findByOutTradeNo(String outTradeNo);

    void save(WeChatTransaction weChatTransaction);
}
